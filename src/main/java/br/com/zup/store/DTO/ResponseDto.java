package br.com.zup.store.DTO;

public class ResponseDto {

    private String response;

    public ResponseDto(String response) {
        super();
        this.response = response;
    }
    
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResponseDto other = (ResponseDto) obj;
        if (response == null) {
            if (other.response != null)
                return false;
        } else if (!response.equals(other.response))
            return false;
        return true;
    }
}