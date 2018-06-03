package com.hailin.blog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * author:hailin
 * Date:2018/5/30
 * Time:8:33
 * @Description
 * @Author
 * @Date
 * @Version
 */
@Document(indexName = "blog", type = "blog", shards = 1, replicas = 0, refreshInterval = "-1")
public class EsBlog implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * Bloges 实体的 id
     */
    @Field(type = FieldType.Long)
    private Long blogId;

    @Field(type = FieldType.text)
    private String title;

    @Field(type = FieldType.text)
    private String summary;


    @Field(type = FieldType.text)
    private String content;

    /**
     *不做全文检索字段
     */
    @Field(type = FieldType.keyword, index = false)
    private String username;
    /**
     *不做全文检索字段
     */
    @Field(type = FieldType.text,index = false)
    private String imageUrl;

    /**
     *不做全文检索字段
     */
    @Field(type = FieldType.Date,index = false)
    private Date createTime;
    /**
     *不做全文检索字段
     * 访问量、阅读量
     */
    @Field(type = FieldType.Integer,index = false)
    private Integer readSize = 0;

    /**
     * 评论量
     *  不做全文检索字段
     */
    @Field(type = FieldType.Integer,index = false)
    private Integer commentSize = 0;

    /**
     *   点赞量
     *  不做全文检索字段
     */
    @Field(type = FieldType.Integer,index = false)
    private Integer voteSize = 0;

    /**
     *  标签
     */
    @Field(type = FieldType.text,fielddata = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String tags;

    protected EsBlog() {
    }

    public EsBlog(String id, Long blogId, String title, String summary, String content, String tags) {
        this.id = id;
        this.blogId = blogId;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.tags = tags;
        this.username = "";
        this.imageUrl = "";
        this.createTime = new Date();
        this.readSize = 0;
        this.commentSize = 0;
        this.voteSize = 0;
    }

    public EsBlog(Long blogId, String title, String summary, String content,
                  String username, String imageUrl, Date createTime,
                  Integer readSize, Integer commentSize, Integer voteSize , String tags) {
        this.blogId = blogId;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.username = username;
        this.imageUrl = imageUrl;
        this.createTime = createTime;
        this.readSize = readSize;
        this.commentSize = commentSize;
        this.voteSize = voteSize;
        this.tags = tags;
    }

    public EsBlog(Blog blog){
        this.blogId = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.username = blog.getUser().getUsername();
        this.imageUrl = blog.getUser().getImageUrl();
        this.createTime = blog.getCreateTime();
        this.readSize = blog.getReadSize();
        this.commentSize = blog.getCommentSize();
        this.voteSize = blog.getVoteSize();
        this.tags = blog.getTags();
    }

    public void update(Blog blog){
        this.blogId = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.username = blog.getUser().getUsername();
        this.imageUrl = blog.getUser().getImageUrl();
        this.createTime = blog.getCreateTime();
        this.readSize = blog.getReadSize();
        this.commentSize = blog.getCommentSize();
        this.voteSize = blog.getVoteSize();
        this.tags = blog.getTags();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "EsBlog{" +
                "id='" + id + '\'' +
                ", blogId=" + blogId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createTime=" + createTime +
                ", readSize=" + readSize +
                ", commentSize=" + commentSize +
                ", voteSize=" + voteSize +
                ", tags='" + tags + '\'' +
                '}';
    }


    public static EsBlog of(Blog blog){
        EsBlog esBlog = new EsBlog();
        esBlog.blogId = blog.getId();
        esBlog.title = blog.getTitle();
        esBlog.summary = blog.getSummary();
        esBlog.content = blog.getContent();
        esBlog.username = blog.getUser().getUsername();
        esBlog.imageUrl = blog.getUser().getImageUrl();
        esBlog.createTime = blog.getCreateTime();
        esBlog.readSize = blog.getReadSize();
        esBlog.commentSize = blog.getCommentSize();
        esBlog.voteSize = blog.getVoteSize();
        esBlog.tags = blog.getTags();
        return esBlog;
    }
}
