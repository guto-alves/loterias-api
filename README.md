<p align="center">
  <img src="https://user-images.githubusercontent.com/48946749/147809259-e7b15a3b-2e90-42c2-abaf-a6cacdc77e03.png">
  <h2 align="center">API Loterias CAIXA</h2>
  <p align="center">
    API Gratuita de resultado de jogos das <a href="http://loterias.caixa.gov.br/wps/portal/loterias">Loterias CAIXA</a>.<br>
  </p>
</p>


## Doação (*Importante!*)

A API é 100% gratuita. Porém ela está hospedada no [Heroku](https://www.heroku.com/) que recentemente [parou de oferecer planos de produtos gratuitos](https://blog.heroku.com/next-chapter), portanto agora há custos para mante-lá.

Então se você achou este projeto interessante ou é útil para você de alguma forma, por favor considere fazer uma doação para ajudar com os custos da hospedagem, qualquer valor ajudará.

**Para doar copie e cole no seu navegador o seguinte link**: https://pag.ae/7ZqNrvmsK

![Donate](https://stc.pagseguro.uol.com.br/public/img/botoes/doacoes/205x30-doar.gif) 

## Exemplos de Retorno
Atualmente o banco de dados contém os jogos das loterias ...

https://loteriascaixa-api.herokuapp.com/api

```
[
  "mega-sena",
  "lotofacil",
  "quina",
  "lotomania",
  "timemania",
  "dupla-sena",
  "loteria-federal",
  "dia-de-sorte",
  "super-sete"
]
```

* **Resultado mais Recente**

```https://loteriascaixa-api.herokuapp.com/api/<loteria>/latest```

Por exemplo da Mega Sena, em: 

https://loteriascaixa-api.herokuapp.com/api/mega-sena/latest

```
{
  "loteria": "mega-sena",
  "nome": "Mega-Sena",
  "concurso": 2431,
  "data": "24/11/2021",
  "local": "ESPAÇO LOTERIAS CAIXA em SÃO PAULO, SP",
  "dezenas": [
    "08",
    "11",
    "22",
    "25",
    "26",
    "36"
  ],
  "premiacoes": [
    {
      "acertos": "Sena",
      "vencedores": 0,
      "premio": "-"
    },
    {
      "acertos": "Quina",
      "vencedores": 46,
      "premio": "35.595,61"
    },
    {
      "acertos": "Quadra",
      "vencedores": 2988,
      "premio": "782,84"
    }
  ],
  "estadosPremiados": [],
  "acumulou": true,
  "acumuladaProxConcurso": "R$ 7 Milhões",
  "dataProxConcurso": "27/11//2021",
  "proxConcurso": 2432,
  "timeCoracao": null,
  "mesSorte": null
}
```

* **Resultado Específico**

```https://loteriascaixa-api.herokuapp.com/api/<loteria>/<concurso>```

Lotofácil, concurso 2027: https://loteriascaixa-api.herokuapp.com/api/lotofacil/2027

```
{
  "loteria": "lotofacil",
  "nome": "Lotofácil",
  "concurso": 2027,
  "data": "02/09/2020",
  "local": "Espaço Loterias Caixa em SÃO PAULO, SP",
  "dezenas": [
    "01",
    "02",
    "03",
    "06",
    "07",
    "09",
    "12",
    "13",
    "16",
    "17",
    "20",
    "21",
    "23",
    "24",
    "25"
  ],
  "premiacoes": [
    {
      "acertos": "15 Pontos",
      "vencedores": 2,
      "premio": "1.617.835,87"
    },
    {
      "acertos": "14 Pontos",
      "vencedores": 408,
      "premio": "1.393,71"
    },
    {
      "acertos": "13 Pontos",
      "vencedores": 13288,
      "premio": "25,00"
    },
    {
      "acertos": "12 Pontos",
      "vencedores": 146003,
      "premio": "10,00"
    },
    {
      "acertos": "11 Pontos",
      "vencedores": 782964,
      "premio": "5,00"
    }
  ],
  "estadosPremiados": [
    {
      "nome": "Maranhão",
      "uf": "MA",
      "vencedores": "1",
      "latitude": "42.4072107",
      "longitude": "-71.3824374",
      "cidades": [
        {
          "cidade": "São Mateus do Maranhão",
          "vencedores": "1",
          "latitude": "-4.030871299999999",
          "longitude": "-44.4654609"
        }
      ]
    },
    {
      "nome": "São Paulo",
      "uf": "SP",
      "vencedores": "1",
      "latitude": "-23.5557714",
      "longitude": "-46.6395571",
      "cidades": [
        {
          "cidade": "Presidente Epitácio",
          "vencedores": "1",
          "latitude": "-21.7668273",
          "longitude": "-52.1096821"
        }
      ]
    }
  ],
  "acumulou": false,
  "acumuladaProxConcurso": "",
  "dataProxConcurso": "",
  "proxConcurso": 2028,
  "timeCoracao": null,
  "mesSorte": null
}
```

-  **Observações sobre o retorno**

Os campos <i><b>timeCoracao</b></i> e <i><b>mesSorte</b></i> só terão algum valor quando a loteria pesquisada for Timemania (timemania) ou Dia de Sorte (dia-de-sorte) respectivamente.

## Documentação da API
 
Para mais informações sobre todas as operações da API acesse: 

https://loteriascaixa-api.herokuapp.com

![image](https://user-images.githubusercontent.com/48946749/144352143-7140d64d-43a9-465c-b12c-7d5d3514ccd5.png)

## Contribuição

Quaisquer contribuições para este repositório são bem-vindas.
