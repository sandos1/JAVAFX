package sample;

public class Members {
    private int registerNumber;
    private String lname;
    private String fname;
    private String DOB;
    private String phone_nmb;
    private String email;
    private String gender;
    private String address;

   public Members(int registerNumber,String lname,String fname,String DOB,String phone_nmb,String email,String gender,String address){
       this.setRegisterNumber(registerNumber);
       this.setLname(lname);
       this.setFname(fname);
       this.setDOB(DOB);
       this.setPhone_nmb(phone_nmb);
       this.setEmail(email);
       this.setGender(gender);
       this.setAddress(address);
   }


    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhone_nmb() {
        return phone_nmb;
    }

    public void setPhone_nmb(String phone_nmb) {
        this.phone_nmb = phone_nmb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
