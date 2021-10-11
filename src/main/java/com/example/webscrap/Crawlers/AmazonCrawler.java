package com.example.webscrap.Crawlers;

import com.example.webscrap.Models.ProductDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonCrawler {

    public List<ProductDto> search(String word) {

        List<ProductDto> productDtoList = new ArrayList<>();
        try {
            System.setProperty("http.proxyHost", "206.177.166.210");
            System.setProperty("http.proxyPort", "1080");
            Document doc = Jsoup.connect("https://www.amazon.com/s?k=" + word) .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").get();
            Elements result = doc.getElementsByClass("s-result-item s-asin sg-col-0-of-12 sg-col-16-of-20 sg-col s-widget-spacing-small sg-col-12-of-16");

            for(Element el:result){
                String prname = el.getElementsByClass("a-size-medium a-color-base a-text-normal").text();
                Element url = el.select("a").first();
                String purl = "https://www.amazon.com/"+  url.attr("href");
              Elements pricespan = el.getElementsByClass("a-price");

                ProductDto productDto = new ProductDto();
                productDto.setName(prname);
                productDto.setUrl(purl);
                for(Element u:pricespan){
                     String price = u.getElementsByClass("a-offscreen").text();
                     if(price.startsWith("$")){
                         productDto.setPrice(price);
                     }
                     else{
                         productDto.setPrice("Not available");
                     }

                }
                productDtoList.add(productDto);

            }




        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return productDtoList;
    }
}
