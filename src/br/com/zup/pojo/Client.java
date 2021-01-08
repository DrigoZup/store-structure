package br.com.zup.pojo;

public class Client {

    private Long cpf;

    private String name;

    private String email;

    private String address;

    private Long phoneNumber;

    private Byte age;

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "[nome=" + name + ",cpf=" + cpf + ", email=" + email + ", endereco=" + address
                + ", telefone=" + phoneNumber + ", idade=" + age + "]";
    }
}
