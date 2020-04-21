package com.cwi.demo.service;

import com.cwi.demo.DAO.AssociadoDAO;
import com.cwi.demo.DTO.AssociadoDTO;
import com.cwi.demo.bean.Associado;
import com.cwi.demo.util.ApiCpf;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoDAO associadoDAO;


    public Associado save(Associado associado) {
        return associadoDAO.save(associado);
    }

    public boolean validaCPF(AssociadoDTO associadoDTO) {
        boolean retorno = false;
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.treinaweb.com.br/ferramentas-para-desenvolvedores/validar/")

                    .build();

            ApiCpf api = retrofit.create(ApiCpf.class);

            Response<ResponseBody> response = api.validaCPF(associadoDTO.getCpf()).execute();

            if (response.body().string().contains(("true")))
                retorno = true;
            else
                retorno = false;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return retorno;
        }
    }


    public Associado findById(Integer id) {
        return associadoDAO.findById(id).orElse(null);
    }
}
