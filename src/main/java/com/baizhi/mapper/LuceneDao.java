package com.baizhi.mapper;

import com.baizhi.entity.Article;
import com.baizhi.entity.Gurn;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LuceneDao {
    public void addArticeLuce(Article product) {
        IndexWriter indexWriter = LuceneUtil.getWriter();
        Document document = getDocFromPro(product);
        try {
            indexWriter.addDocument(document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    public void deleteArticle(Integer id) {
        IndexWriter indexWriter = LuceneUtil.getWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id.toString()));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    public void updateArticle(Article p) {
        IndexWriter indexWriter = LuceneUtil.getWriter();
        Document document = getDocFromPro(p);
        try {
            indexWriter.updateDocument(new Term("id", p.getId().toString()), document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    public List<Article> queryAllArt(String param) {

        //分页处理。第一个是每页显示个数，第二个是页码
        int pageSize = 10;
        int pageNum = 1;


        IndexSearcher indexSearcher = LuceneUtil.getSearcher();
        List<Article> list = null;


        //多属性查询，基于一句话查询，配置相关的query
        //注意  1  需要保持分词器的一致性  2  导入相关的jar
        String[] row = {"title", "content", "author"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, row, new IKAnalyzer());
        Query query = null;
        try {
            query = multiFieldQueryParser.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //为查询的关键词进行高亮处理
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);
        try {

            TopDocs topDocs = indexSearcher.search(query, pageNum * pageSize);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            list = new ArrayList<>();
            for (int i = (pageNum - 1) * pageSize; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Article product = getProFromDoc(document, highlighter);
                System.out.println(product);
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Document getDocFromPro(Article p) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(p.getPubDate());
        Document document = new Document();
        document.add(new StringField("id", p.getId().toString(), Field.Store.YES));
        document.add(new TextField("title", p.getTitle(), Field.Store.YES));
        document.add(new StringField("insertImg", p.getInsertImg(), Field.Store.YES));
        document.add(new TextField("content", p.getContent(), Field.Store.YES));
        document.add(new StringField("pubDate", time, Field.Store.YES));
        document.add(new StringField("author", p.getG().getDharma(), Field.Store.YES));
        return document;
    }

    public Article getProFromDoc(Document document, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        Article p = new Article();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = highlighter.getBestFragment(new IKAnalyzer(), "id", document.get("id"));
        if (id == null) {
            p.setId(Integer.parseInt(document.get("id")));
        } else {
            p.setId(Integer.parseInt(id));
        }
        String title = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("title"));
        if (title == null) {
            p.setTitle(document.get("title"));
        } else {
            p.setTitle(title);
        }
        String insertImg = highlighter.getBestFragment(new IKAnalyzer(), "insertImg", document.get("insertImg"));
        if (insertImg == null) {
            p.setInsertImg(document.get("insertImg"));
        } else {
            p.setInsertImg(insertImg);
        }
        String content = highlighter.getBestFragment(new IKAnalyzer(), "content", document.get("content"));
        if (content == null) {
            System.out.println(content + "----");
            p.setContent(document.get("content"));
        } else {
            System.out.println(content + "++++");
            p.setContent(content);
        }
        String pubDate = highlighter.getBestFragment(new IKAnalyzer(), "pubDate", document.get("pubDate"));
        if (pubDate == null) {
            try {
                p.setPubDate(sdf.parse(document.get("pubDate")));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                p.setPubDate(sdf.parse(pubDate));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        String author = highlighter.getBestFragment(new IKAnalyzer(), "author", document.get("author"));
        Gurn g = new Gurn();
        p.setG(g);
        if (author == null) {
            p.getG().setDharma(document.get("author"));
        } else {
            p.getG().setDharma(author);
        }

        return p;
    }
}
