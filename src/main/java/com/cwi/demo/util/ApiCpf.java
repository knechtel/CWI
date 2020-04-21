package com.cwi.demo.util;


import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.POST;
import retrofit2.http.Query;


public  interface ApiCpf {

    @POST("https://www.treinaweb.com.br/ferramentas-para-desenvolvedores/validar/cpf?")
    public Call<ResponseBody> validaCPF( @Query("numero") String cpf);


}
