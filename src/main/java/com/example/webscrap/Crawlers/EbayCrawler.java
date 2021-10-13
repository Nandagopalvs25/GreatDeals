package com.example.webscrap.Crawlers;

import com.example.webscrap.Models.ProductDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EbayCrawler {


    public List<ProductDto> search(String word){


        List<ProductDto> productDtoList = new ArrayList<>();

        try {
            Map<String, String> head = new HashMap<>();
            head.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            head.put("referer", "https://www.amazon.com/s?k=nike+shoes+men&crid=28WRS5SFLWWZ6&sprefix=nike%2Caps%2C357&ref=nb_sb_ss_organic-diversity_2_4");

            System.setProperty("http.proxyHost", "206.177.166.210");
            System.setProperty("http.proxyPort", "1080");
            Document doc = Jsoup.connect("https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313&_nkw=" + word).headers(head).get();
            Elements result = doc.getElementsByClass("s-item s-item__sep-on-bottom s-item--watch-at-corner");

            for(Element u: result){

                Elements  url = u.getElementsByClass("s-item__link");
                ProductDto productDto = new ProductDto();
                productDto.setName(url.attr("class","s-item__title").text());
                productDto.setUrl(url.attr("href"));
                productDto.setPrice(u.getElementsByClass("s-item__detail s-item__detail--primary").first().text());
                productDtoList.add(productDto);






            }
            return productDtoList;



        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return productDtoList;
    }
}
