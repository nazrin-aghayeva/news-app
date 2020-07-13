package com.feed.news.crawler.parsers;

import com.feed.news.crawler.RestTemplateService;
import com.feed.news.entity.db.Article;
import com.feed.news.crawler.DateTimeFormats;
import com.feed.news.crawler.JsoupParser;
import com.feed.news.crawler.Website;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsiderParser extends RestTemplateService implements JsoupParser {

    List<Article> articles = new ArrayList<>();;
    Document doc;

//    public InsiderParser() {
//        this.articles = new ArrayList();
//        doc = rootPage("https://www.insider.com/news");
//    }

    @SneakyThrows
    @Override
    public List<Article> getArticles() {
         Document doc = Jsoup.connect("https://www.insider.com/news").get();
        Elements elements = doc.getElementsByClass("featured-post");
        for (Element element : elements) {
            String header = element.select(".tout-title-link").text();
            String content = element.select(".tout-copy").text();
            String link = "https://www.insider.com/".concat(element.select(".tout-title-link").attr("href"));
            String image = element.select(".lazy-image").first().attr("data-srcs").split("\":\\{")[0].substring(2);
            LocalDate date = convertStringToDate(element.select(".tout-timestamp").text(), DateTimeFormats.ISO_INSTANT_FORMAT);
            articles.add(new Article(header, content, link, image, date, Website.Insider));

        }
        return articles;
    }


}
