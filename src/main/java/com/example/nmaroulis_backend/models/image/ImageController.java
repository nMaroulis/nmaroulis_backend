package com.example.nmaroulis_backend.models.image;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@RestController
class ImageController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";

    private final ImageRepository repository;

    ImageController(ImageRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @PostMapping("/images/upload")
    public String singleFileUpload(@RequestParam("picture") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/images/uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER
                    + file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf('/'), file.getOriginalFilename().lastIndexOf('.'))
                    + new Random().nextInt(1 << 20)
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
            );
            Files.write(path, bytes);
            Image img = new Image(path.toString());
            repository.save(img);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/images/uploadStatus";
    }

    @GetMapping("/images/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/images")
    List<Image> all() {
        return repository.findAll();
    }

    @PostMapping("/images")
    Image newImage(@RequestBody Image newImage) {
        return repository.save(newImage);
    }

    // Single item

    @GetMapping("/images/{id}")
    Image one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(id));
    }


    @PutMapping("/images/{id}")
    Image replaceImage(@RequestBody Image newImage, @PathVariable Long id) {

        return repository.findById(id)
                .map(Image -> {
                    Image.setPath(newImage.getPath());
                    return repository.save(Image);
                })
                .orElseGet(() -> {
                    newImage.setId(id);
                    return repository.save(newImage);
                });
    }

    @DeleteMapping("/images/{id}")
    void deleteImage(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
