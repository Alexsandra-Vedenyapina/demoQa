package qa.demo.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseModelPojo {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    String name;
    String job;
    String id;
    String createdAt;
  /*  MetaInfo _meta;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public MetaInfo get_meta() {
        return _meta;
    }

    public void set_meta(MetaInfo _meta) {
        this._meta = _meta;
    }

    String context;
*/
}
