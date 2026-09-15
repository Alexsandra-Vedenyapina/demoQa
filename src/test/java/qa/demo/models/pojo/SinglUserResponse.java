package qa.demo.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SinglUserResponse {
//    {
//        "data": {
//        "id": 2,
//                "email": "janet.weaver@reqres.in",
//                "first_name": "Janet",
//                "last_name": "Weaver",
//                "avatar": "https://reqres.in/img/faces/2-image.jpg"
//    },
//        "support": {
//        "url": "https://benhowdle.im/first-cto-playbook?utm_source=reqres&utm_medium=json&utm_campaign=referral",
//                "text": "Become a better CTO. A playbook of painful stories and practical advice from a two-time startup CTO."
//    },
//        "_meta": {
//        "powered_by": "ReqRes",
//                "docs_url": "https://app.reqres.in/documentation",
//                "upgrade_url": "https://app.reqres.in/upgrade",
//                "example_url": "https://app.reqres.in/examples/notes-app",
//                "variant": "v1_b",
//                "message": "This is a read-only demo endpoint. Sign up to create your own collections with full CRUD and auth.",
//                "cta": {
//            "label": "Get started",
//                    "url": "https://app.reqres.in/upgrade"
//        },
//        "context": "legacy_success"
//    }
//    }
// url,text, powered_by, docs_url, upgrade_url, example_url, variant, message, label,cta;


    public UserData data;
    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }



}
