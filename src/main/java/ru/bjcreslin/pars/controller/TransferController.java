package ru.bjcreslin.pars.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import ru.bjcreslin.pars.Service.StorageService;
import ru.bjcreslin.pars.Service.XLSService;
import ru.bjcreslin.pars.model.ProductOur;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.util.List;

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
//todo сделать стартовую страницу загрузки файла

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

           //todo файл пустой. сдеалть реализацию
        } else {

            try {
               List<ProductOur> productOurList=XLSService.getBaza8List(file.getInputStream());


            } catch (IOException e) {
               //todo реализация при ексепшене
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
