package org.example.controller;

import org.example.dto.ProductDTO;
import org.example.dto.ProductResponseSlNO;
import org.example.model.ImageProd;
import org.example.model.ProductModel;
import org.example.service.Producraddservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    Producraddservice producraddservice;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/product/seller/add")
    public ResponseEntity<ProductModel> productadd(@RequestBody @Valid ProductDTO productDTO){
        ProductModel prod1=producraddservice.productaddorupdate(productDTO.to());
        return new ResponseEntity<>(prod1, HttpStatus.CREATED);
    }
    @PostMapping("/product/seller/{productcat}")
    public ResponseEntity<List<ProductModel>> productfetchcat(@PathVariable String productcat){
        List<ProductModel> pr2=producraddservice.productfetchcat(productcat);
        return new ResponseEntity<>(pr2,HttpStatus.ACCEPTED);
    }
    @PostMapping("/product/sel/{slno}")
    public ResponseEntity<ProductResponseSlNO> getProductwithSerialNo(@PathVariable String slno){
        ProductModel pro=producraddservice.getProduct(slno);
        List<byte[]> img=new ArrayList<>();
        for(ImageProd imageProd:pro.getImage()){
            byte[] imageBytes = new byte[0];
            try {
                imageBytes = getImageBytes(imageProd.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            img.add(imageBytes);
        }
        ProductResponseSlNO productResponseSlNO=modelMapper.map(pro,ProductResponseSlNO.class);
        productResponseSlNO.setImages(img);
        return new ResponseEntity<>(productResponseSlNO,HttpStatus.ACCEPTED);
    }
    private byte[] getImageBytes(String imageFilename) throws IOException {
        Path imagePath= Paths.get("C:\\JavaScript_FE\\FSJS\\react_new\\ecommercen\\src\\image\\" + imageFilename);
        return Files.readAllBytes(imagePath);
    }

}
