package com.example.nmaroulis_backend.models.user;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.nmaroulis_backend.models.post.Post;
import com.example.nmaroulis_backend.models.post.PostRepository;
import com.example.nmaroulis_backend.title.Title;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


@RestController
class UserController {

    private final UserRepository repository;

    @Autowired
    private PostRepository postRepository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setFname(newUser.getFname());
                    user.setLname(newUser.getLname());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setResidence(newUser.getResidence());
                    user.setEducation(newUser.getEducation());
                    user.setWork(newUser.getWork());
                    user.setGender(newUser.getGender());
                    user.setTitle(newUser.getTitle());
                    user.setMember_since(newUser.getMember_since());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }


    @PostMapping("user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        User u = repository.findByUsername(username);
        if (u != null) {       // ELEGXOS EAN YPARXEI O USER -> FTIAXNEI TO TOKEN

            if(Objects.equals(u.getPassword(), pwd)){  //EAN O KWDIKOS EINAI SWSTOS
                String token = getJWTToken(username);
                u.setToken(token);
                return u;
            }
            else {
                return null;
            }
        }
        else{
            return null;
        }
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }


    @PostMapping("/newuser")
    User register(@RequestParam("user") String username, @RequestParam("password") String pwd,
                  @RequestParam("fname") String fname, @RequestParam("lname") String lname,
                  @RequestParam("email") String email, @RequestParam("gender") String gender,
                  @RequestParam("title") String title, @RequestParam("residense") String residense) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String member_since = dtf.format(now); // pros8etei to pote egine to signup
        return repository.save(new User(username, pwd, fname, lname, email, gender, new Title(title), residense,member_since));
    }

    @GetMapping("/user/{userId}/connections")
    List <User> getAllConnectionsByUserId(@PathVariable (value = "userId") Long userId) {

        User u = repository.findById(userId).get(); // to get() voh8aei sto na epistrepsei user kai oxi apla Optional
        List <User> connections = u.getConnections();
        return connections;
    }

    @PutMapping("/user/{userId}/connection/{connectionId}")  //  DEPRECATED
    User setConnectionByUserId(@PathVariable (value = "userId") Long userId,
                                      @PathVariable (value = "connectionId") Long connectionId) {
        User u = repository.findById(userId).get();
        List <User> connections = u.getConnections();
        User new_connection = repository.findById(connectionId).get();
        connections.add(new_connection);
        u.setConnections(connections);
        return repository.save(u);

    }


    @GetMapping("/user/{userId}/requests")
    List <User> getAllRequestsByUserId(@PathVariable (value = "userId") Long userId) {

        User u = repository.findById(userId).get(); // to get() voh8aei sto na epistrepsei user kai oxi apla Optional
        List <User> request_connections = u.getRequest_connections();
        return request_connections;
    }

    @PostMapping("/user/{userId}/request/{connectionId}")
    User setRequestsByUserId(@PathVariable (value = "userId") Long userId,
                               @PathVariable (value = "connectionId") Long connectionId) {
        User u = repository.findById(userId).get();  // o user pou ekane thn aithsh - user1
        List <User> request_connections = u.getRequest_connections();  // ta requests tou user1
        User new_request_connection = repository.findById(connectionId).get(); // o user2
        request_connections.add(new_request_connection); // pros8esh tou user2 sta request tou user1
        u.setRequest_connections(request_connections);   //

        List <User> response_connections = new_request_connection.getResponse_connections();  // pairnoume ta responses tou user 2
        response_connections.add(u); // pros8etoume ton user1 sta responses tou user2
        new_request_connection.setResponse_connections(response_connections); //

        repository.save(new_request_connection);
        return repository.save(u);

    }


    @GetMapping("/user/{userId}/responses")
    List <User> getAllResponsesByUserId(@PathVariable (value = "userId") Long userId) {

        User u = repository.findById(userId).get();
        List <User> response_connections = u.getResponse_connections();
        return response_connections;
    }

    @PostMapping("/user/{userId}/response/{connectionId}")  // kanei accept ton xrhsth
    User setResponsesByUserId(@PathVariable (value = "userId") Long userId,
                             @PathVariable (value = "connectionId") Long connectionId) {

        User u = repository.findById(userId).get();  // o user pou ekane thn aithsh - user1
        List <User> response_connections = u.getResponse_connections();  // ta requests tou user1
        List <User> connections = u.getConnections();
        User u1 = repository.findById(connectionId).get(); // o user2

        response_connections.remove(u1);                    // afairesh tou user2 sta request tou user1
        u.setResponse_connections((response_connections));   //


        connections.add(u1);
        u.setConnections(connections);
        //
        //        List <User> connections1 = u1.getConnections();
        //        List <User> request_connections1 = u1.getRequest_connections();
        //        request_connections1.remove(u);
        //        u1.setResponse_connections(request_connections1);
        //        repository.save(u1);
        //        connections1.add(u);
        //        u1.setConnections(connections1);

        return repository.save(u);

    }

    @PostMapping("/user/{userId}/response/{connectionId}/notify_connection")  // kanei accept ton xrhsth
    User setResponsesByUserIdToConnection(@PathVariable (value = "userId") Long userId,
                              @PathVariable (value = "connectionId") Long connectionId) {

        User u = repository.findById(userId).get();  // o user pou ekane thn aithsh - user1
        User u1 = repository.findById(connectionId).get(); // o user2

        List <User> connections1 = u1.getConnections();
        List <User> request_connections1 = u1.getRequest_connections();
        request_connections1.remove(u);
        u1.setRequest_connections(request_connections1);
        connections1.add(u);
        u1.setConnections(connections1);

        return repository.save(u1);

    }


    @GetMapping("/user/{userId}/timeline")
    List <Post> getTimelineByUserId(@PathVariable (value = "userId") Long userId) {

        User u = repository.findById(userId).get();

        List <User> connections = u.getConnections();

        if(connections.isEmpty()){
            return null;
        }
        else{

            List <Post> posts = null;

            for (int i = 0; i < connections.size(); i++) {

                List <Post> tmp_posts = postRepository.findByUserId(connections.get(i).getId());

                if(!tmp_posts.isEmpty()){
                    posts.addAll(tmp_posts);
                }
            }

            return posts;

        }
    }



}
