package com.sdunews.manager;

import com.sdunews.redis.SduNewsDAO;
import com.sdunews.model.bo.SduNewsBO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SduNewsManager {
    private static final int CRAWL_INTERVAL = 60 * 60 * 1000;
    private static final int CRAWL_TIMEOUT = 30 * 1000;
    private static final String CRAWL_TARGET_URL = "https://www.bkjx.sdu.edu.cn/index/gztz.htm";

    private final SduNewsDAO sduNewsDAO;

    public SduNewsManager(SduNewsDAO sduNewsDAO) {
        this.sduNewsDAO = sduNewsDAO;
    }

    public List<SduNewsBO> getAllNews() {
        Map<String, String> map = sduNewsDAO.getAllNews();

        List<SduNewsBO> newsList = new ArrayList<>(map.size());
        for (String key : map.keySet()) {
            String value = map.get(key);
            String date = value.substring(0, 10);
            String url = value.substring(10);
            newsList.add(new SduNewsBO(key, date, url));
        }

        return newsList;
    }

    @Scheduled(fixedDelay = CRAWL_INTERVAL)
    public void crawlNews() {
        Document pageDoc = fetchPage();
        if (pageDoc == null) {
            return;
        }

        List<SduNewsBO> newsList = parseNews(pageDoc);
        sduNewsDAO.clear();
        for (SduNewsBO news : newsList) {
            sduNewsDAO.addNews(news.getTitle(), news.getDate() + news.getUrl());
        }
    }

    private Document fetchPage() {
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(CRAWL_TARGET_URL), CRAWL_TIMEOUT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return doc;
    }

    private List<SduNewsBO> parseNews(Document pageDoc) {
        Elements elements = pageDoc.body()
                .getElementsByClass("newscontent").get(0)
                .getElementsByClass("leftNews3");

        List<SduNewsBO> newsList = new ArrayList<>();
        for (Element element : elements) {
            if (!element.attr("id").startsWith("line")) {
                  continue;
            }

            Element aTag = element.getElementsByTag("div").get(2);
            Element dateTag = element.getElementsByTag("div").get(3);
            String url = aTag.getElementsByTag("a").get(0).attr("href");
            if(!(url.startsWith("http"))){
                url = "https://www.bkjx.sdu.edu.cn/"+url;
            }
            String title = aTag.text();
            String date = dateTag.text().substring(1,11);
            String date1 = date.substring(0,4)+"/"+date.substring(5,7)+"/"+date.substring(8,10);
            newsList.add(new SduNewsBO(title, date1, url));
        }

        return newsList;
    }
}
