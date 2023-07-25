package com.example.backend.controller

import com.example.backend.dto.product.ProductDto
import com.example.backend.services.CategoryService
import com.example.backend.services.FileUploadService
import com.example.backend.services.ProductService
import com.example.backend.xtraa.ApiResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/product")
class ProductController {
    @Autowired
    private lateinit var productService: ProductService
    @Autowired
    private lateinit var categoryService: CategoryService

    private val logger = LoggerFactory.getLogger(ProductController::class.java)

    @GetMapping("/")
    fun getProducts(): ResponseEntity<List<ProductDto>> {
        val body = productService.listProducts();
        return ResponseEntity(body, HttpStatus.OK)
    }

    @PostMapping("/add")
    fun addProduct(@RequestParam("file") file: MultipartFile, @RequestParam("productDto") jsonStr: String): ResponseEntity<ApiResponse> {
        logger.error("inside add product $jsonStr")
        val objectMapper = ObjectMapper()
        val productDto: ProductDto = objectMapper.readValue(jsonStr, ProductDto::class.java)

        logger
            .error("product category id ${productDto.name}${productDto.description} cat id ${productDto.categoryId}")
        val optionalCategory = categoryService.readCategory(productDto.categoryId)
        val imageUri = FileUploadService().uploadImage(file)
        productDto.imageURL = imageUri.split(' ')[1]

        logger.info("$optionalCategory")
        if (optionalCategory == null)
            return ResponseEntity<ApiResponse>(ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT)
        val category = optionalCategory.get()
        productService.addProduct(productDto, category)
        logger.info("product Msg $productDto")
        return ResponseEntity(ApiResponse(true, "Product has been added"), HttpStatus.CREATED)
    }

    @PostMapping("/update/{productId}")
    fun updateProduct(
        @PathVariable("productId") productId: Int,
        @RequestBody productDto: ProductDto
    ): ResponseEntity<ApiResponse> {
        val optionalCategory = categoryService.readCategory(productId)
        if (optionalCategory.isPresent)
            return ResponseEntity<ApiResponse>(ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT)
        val category = optionalCategory.get()
        productService.updateProduct(productId, productDto, category)
        return ResponseEntity(ApiResponse(true, "Product has been updated"), HttpStatus.OK)
    }

}
























