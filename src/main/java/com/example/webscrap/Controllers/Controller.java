package com.example.webscrap.Controllers;

import com.example.webscrap.Crawlers.AmazonCrawler;
import com.example.webscrap.Crawlers.EbayCrawler;
import com.example.webscrap.Crawlers.WalmartCrawler;
import com.example.webscrap.Models.ProductDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class Controller {

    @FXML
    private TextField sfield;

    @FXML
    private ListView box1;

    @FXML
    private ListView box11;

    @FXML
    private ListView box12;


    public void search(ActionEvent e) throws IOException {
        amazonSearch(sfield.getText());
        walmartSearch(sfield.getText());
        ebaySearch(sfield.getText());

    }

    public void amazonSearch(String word) {
        box1.getItems().clear();
        AmazonCrawler aws = new AmazonCrawler();
        List<ProductDto> productDtoList = aws.search(word);

        for (ProductDto productDto : productDtoList) {
            TextField textField = new TextField(productDto.getName());
            textField.setEditable(false);
            TextField textField1 = new TextField(productDto.getUrl());
            textField1.setEditable(false);
            TextField textField2 = new TextField(productDto.getPrice());
            textField2.setEditable(false);

            textField.setPrefWidth(230);
            textField1.setPrefWidth(230);
            textField2.setPrefWidth(230);

            HBox hBox = new HBox(20, textField, textField1,textField2);
            box1.getItems().add(hBox);




        }
    }

    public void walmartSearch(String word){
        box11.getItems().clear();
        WalmartCrawler walmartCrawler = new WalmartCrawler();
        List<ProductDto> productDtoList = walmartCrawler.search(word);

        for (ProductDto productDto : productDtoList) {
            TextField textField = new TextField(productDto.getName());
            textField.setEditable(false);
            TextField textField1 = new TextField(productDto.getUrl());
            textField.setEditable(false);
            TextField textField2 = new TextField(productDto.getPrice());
            textField.setEditable(false);

            textField.setPrefWidth(230);
            textField1.setPrefWidth(230);
            textField2.setPrefWidth(230);

            HBox hBox = new HBox(20, textField, textField1,textField2);
            box11.getItems().add(hBox);




        }

    }

    public void ebaySearch(String word){
        box12.getItems().clear();
        EbayCrawler ebayCrawler = new EbayCrawler();
        List<ProductDto> productDtoList = ebayCrawler.search(word);

        for (ProductDto productDto : productDtoList) {
            TextField textField = new TextField(productDto.getName());
            textField.setEditable(false);
            TextField textField1 = new TextField(productDto.getUrl());
            textField.setEditable(false);
            TextField textField2 = new TextField(productDto.getPrice());
            textField.setEditable(false);

            textField.setPrefWidth(230);
            textField1.setPrefWidth(230);
            textField2.setPrefWidth(230);

            HBox hBox = new HBox(20, textField, textField1,textField2);
            box12.getItems().add(hBox);




        }

        if(productDtoList.size()==0){
            Label test = new Label("No products available");
            test.setTextFill(Color.color(1, 0, 0));
            box12.getItems().add(test);
        }

    }


}
