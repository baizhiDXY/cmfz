package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = articleService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("add")
    public void add(Article article) {
        articleService.add(article);
    }

    @RequestMapping("update")
    public void update(Article article) {
        articleService.update(article);
    }

    @RequestMapping("del")
    public void del(String [] id){
        articleService.delete(id);
    }

    @RequestMapping("es")
    public List<Article> queryByes(String esvalue) {
        List<Article> articles = articleService.queryByes(esvalue);
        return articles;
    }
}
