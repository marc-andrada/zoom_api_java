package service;

import org.json.JSONObject;

public class Member {

    private JSONObject member;

    public Member(Object member){

        this.member = ((JSONObject) member);

    }

    public String getID(){
        if(member.has("id")){
            return member.getString("id");
        }
        else{return "";}

    }

    public String getEmail(){

        if(member.has("email")){
            return member.getString("email");
        }
        else { return ""; }
    }

    public String getFirstName(){

        if(member.has("first_name")){
            return member.getString("first_name");
        }
        else { return ""; }

    }

    public String getLastName(){

        if(member.has("last_name")){
            return member.getString("last_name");
        }
        else { return ""; }
    }

    public String getRole(){

        if(member.has("role")){
            return member.getString("role");
        }
        else { return ""; }
    }

    public String printMember(){

        String memberInfo =
                "\nMember: \"" + this.getFirstName() + " " + this.getLastName() + "\"" +
                        "\nID: " + this.getID() +
                        "\nEmail: " + this.getEmail() +
                        "\nRole: " + this.getRole();

        return memberInfo;
    }

}
