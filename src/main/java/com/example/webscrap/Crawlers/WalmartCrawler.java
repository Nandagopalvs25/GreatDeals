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

public class WalmartCrawler {


    public List<ProductDto> search(String word) {

        List<ProductDto> productDtoList = new ArrayList<>();

        try {
            Map<String, String> head = new HashMap<>();
            head.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            head.put("referer", "https://www.amazon.com/s?k=nike+shoes+men&crid=28WRS5SFLWWZ6&sprefix=nike%2Caps%2C357&ref=nb_sb_ss_organic-diversity_2_4");

            System.setProperty("http.proxyHost", "206.177.166.210");
            System.setProperty("http.proxyPort", "1080");
            Document doc = Jsoup.connect("https://www.walmart.com/search?q=" + word).headers(head).get();
            Elements result = doc.getElementsByClass("mb1 ph1 pa0-xl bb b--near-white w-25");
            for (Element u : result) {
                Element url = u.select("a").first();

                ProductDto productDto = new ProductDto();
                productDto.setName(url.getElementsByClass("w_Dr").text());
                String product_url = url.attr("href");
                if (product_url.startsWith("/ip")) {
                    productDto.setUrl("https://www.walmart.com/" + product_url);
                } else {
                    productDto.setUrl("Not available");
                }

                productDto.setPrice(u.getElementsByClass("b black f5 mr1 mr2-xl lh-copy f4-l").text());
                productDtoList.add(productDto);

            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return productDtoList;

    }

}
