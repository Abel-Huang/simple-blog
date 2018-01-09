package cn.abelib.blog.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * Created by abel on 2018/1/4.
 * 文件实体类
 */
@Document
public class File {
    @Id
    private String id;
    // 文件名
    private String name;
    // 文件类型
    private String contentType;
    // 文件大小
    private long size;
    // 上传日期
    private Date uploadDate;
    // 文件MD5值
    private String md5;
    // 文件内容
    private byte [] content;
    // 文件路径
    private String path;

    protected File(){

    }

    public File(String name, String contentType, long size, byte [] content){
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isEmpty(){
        return this.content == null || this.content.length==0;
    }

    @Override
    public boolean equals(Object object){
        if (object == this){
            return true;
        }
        if (object == null || this.getClass() != object.getClass()){
            return false;
        }
        File file = (File)object;
        return Objects.equals(size, file.size)
                && Objects.equals(content, file.content)
                && Objects.equals(contentType, file.contentType)
                && Objects.equals(uploadDate, file.uploadDate)
                && Objects.equals(md5, file.md5)
                && Objects.equals(id, file.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, contentType, size, uploadDate, md5, id);
    }

    @Override
    public String toString(){
        return "File{"
                + "name='" + name + '\''
                + ", contentType='" + contentType + '\''
                + ", size=" + size
                + ", uploadDate=" + uploadDate
                + ", md5='" + md5 + '\''
                + ", id='" + id + '\''
                + '}';
    }
}
