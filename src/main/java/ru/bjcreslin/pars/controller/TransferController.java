package ru.bjcreslin.pars.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bjcreslin.pars.Service.StorageService;
import ru.bjcreslin.pars.Service.XLSService;
import ru.bjcreslin.pars.controller.controllerExceptions.StorageFileNotFoundException;
import ru.bjcreslin.pars.model.ProductOur;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/sntbLoadXLS")
@Controller
public class TransferController {

    private final StorageService storageService;

    @Autowired
    public TransferController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("")
    /**
     * Главное меню для перемещения товара со склада Центральный на выставкаСПШ
     */
    public String index(Model model) {


        return "transfer";
    }

    @Bean(name = "commonsMultipartResolver")
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("10MB");

        return factory.createMultipartConfig();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart MultipartFile file) {

        if (file.isEmpty()) {

            /**Doit  если файл пустой**/
        } else {

            try {
               List<ProductOur> productOurList=XLSService.getBaza8List(file.getInputStream());


            } catch (IOException e) {
                /** Doit если exception
                 *
                 */
            }

        }


        return "index";
    }


}
//
//    }
//
//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
//
//    @PostMapping("/")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect://index";
//    }
//
//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }
//
//
//}
