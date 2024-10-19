package com.example.controller;

import com.example.service.DataProcessingService;
import com.example.service.ModelTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class FileUploadController {

    @Autowired
    private DataProcessingService dataProcessingService;

    @Autowired
    private ModelTrainingService modelTrainingService;

    private final String UPLOAD_DIR = "datasets/";

    @GetMapping
    public String index() {
        return "base"; // Render base.html
    }

    @GetMapping("/upload")
    public String uploadForm() {
        return "upload"; // Render upload.html
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "No file selected");
            return "redirect:/upload";
        }

        try {
            dataProcessingService.saveFile(file, UPLOAD_DIR);
            redirectAttributes.addFlashAttribute("message", "File successfully uploaded!");
            return "redirect:/eda/" + file.getOriginalFilename();
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "File upload failed!");
            return "redirect:/upload";
        }
    }

    @GetMapping("/eda/{filename}")
    public String eda(@PathVariable String filename, Model model) throws IOException {
        var summary = dataProcessingService.performEDA(UPLOAD_DIR + filename);
        model.addAttribute("summary", summary);
        model.addAttribute("filename", filename);
        return "eda"; // Render eda.html
    }

    @GetMapping("/features/{filename}")
    public String featureSelection(@PathVariable String filename, Model model) throws IOException {
        var columns = dataProcessingService.getColumns(UPLOAD_DIR + filename);
        model.addAttribute("columns", columns);
        model.addAttribute("filename", filename);
        return "feature_selection"; // Render feature_selection.html
    }

    @PostMapping("/features/{filename}")
    public String selectFeatures(@PathVariable String filename, @RequestParam("features") String[] features) {
        String selectedFeatures = String.join(",", features);
        return "redirect:/train/" + filename + "/" + selectedFeatures;
    }

    @GetMapping("/train/{filename}/{features}")
    public String trainModel(@PathVariable String filename, @PathVariable String features, Model model) throws IOException {
        var accuracy = modelTrainingService.trainModel(UPLOAD_DIR + filename, features.split(","));
        model.addAttribute("accuracy", accuracy);
        return "results"; // Render results.html
    }

    @GetMapping("/results")
    public String results() {
        return "results"; // Render results.html
    }
}
