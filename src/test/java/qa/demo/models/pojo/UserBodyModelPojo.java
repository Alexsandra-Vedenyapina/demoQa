package qa.demo.models.pojo;

public class UserBodyModelPojo {
    //"{\n" +
    //                "  \"name\": \""+nameUser+"\",\n" +
    //                "  \"job\": \""+jobUser+"\"\n" +
    //                "}";

     String name, job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
