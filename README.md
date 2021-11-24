# API Loterias CAIXA

API Gratuita de resultado de jogos das [Loterias CAIXA](http://loterias.caixa.gov.br/wps/portal/loterias).

Espero melhorar a API com o tempo. Por enquanto, as mudanças nem sempre serão compatíveis com versões anteriores.

## Exemplos de Retorno
Atualmente o banco de dados contém apenas os jogos das loterias ...

https://loterias-gutotech.herokuapp.com/api

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

```https://loterias-gutotech.herokuapp.com/api/<loteria>/latest```

Por exemplo da Mega Sena, em: 

https://loterias-gutotech.herokuapp.com/api/mega-sena/latest

```
{
  "loteria": "mega-sena",
  "nome": "Mega-Sena",
  "concurso": 2430,
  "data": "20/11/2021",
  "local": "ESPAÇO LOTERIAS CAIXA em SÃO PAULO, SP",
  "dezenas": [
    "19",
    "26",
    "39",
    "45",
    "46",
    "56"
  ],
  "premiacoes": [
    {
      "acertos": "Sena",
      "vencedores": 1,
      "premio": "39.690.444,50"
    },
    {
      "acertos": "Quina",
      "vencedores": 37,
      "premio": "96.493,78"
    },
    {
      "acertos": "Quadra",
      "vencedores": 4109,
      "premio": "1.241,27"
    }
  ],
  "acumulado": false,
  "acumuladaProxConcurso": "R$ 3 Milhões",
  "dataProxConcurso": "24/11/2021",
  "proxConcurso": 2431,
  "timeCoracao": null,
  "mesSorte": null
}
```

* **Resultado Específico**

Lotofácil, concurso 2013: https://loterias-gutotech.herokuapp.com/api/lotofacil/2013

```
{
  "loteria": "lotofacil",
  "nome": "Lotofácil",
  "concurso": 2013,
  "data": "17/08/2020",
  "local": "Espaço Loterias Caixa em SÃO PAULO, SP",
  "dezenas": [
    "01",
    "02",
    "04",
    "07",
    "08",
    "10",
    "11",
    "14",
    "16",
    "19",
    "20",
    "21",
    "23",
    "24",
    "25"
  ],
  "premiacoes": [
    {
      "acertos": "15 Pontos",
      "vencedores": 5,
      "premio": "547.050,76"
    },
    {
      "acertos": "14 Pontos",
      "vencedores": 578,
      "premio": "905,44"
    },
    {
      "acertos": "13 Pontos",
      "vencedores": 16161,
      "premio": "25,00"
    },
    {
      "acertos": "12 Pontos",
      "vencedores": 169887,
      "premio": "10,00"
    },
    {
      "acertos": "11 Pontos",
      "vencedores": 825856,
      "premio": "5,00"
    }
  ],
  "acumulado": false,
  "acumuladaProxConcurso": "",
  "dataProxConcurso": "",
  "proxConcurso": 2014,
  "timeCoracao": null,
  "mesSorte": null
}
```

## Documentação da API
 
Para mais informações sobre todas as operações da API acesse: 

https://loterias-gutotech.herokuapp.com

## Contribuição

Quaisquer contribuições para este repositório são bem-vindas.
