# API Loterias CAIXA

API Gratuita de resultado de jogos das [Loterias CAIXA](http://loterias.caixa.gov.br/wps/portal/loterias).

Espero melhorar a API com o tempo. Por enquanto, as mudanças nem sempre serão compatíveis com versões anteriores.

## Exemplos de Retorno
Atualmente o banco de dados contém apenas os jogos das loterias ...

https://loterias-api-gutotech.herokuapp.com/api/v0
```
[
  "megasena",
  "lotofacil",
  "quina",
  "lotomania"
]
```

* **Resultado mais Recente**

Por exemplo da Mega Sena, em: https://loterias-api-gutotech.herokuapp.com/api/v0/megasena/latest
```
{
  "concurso": 2344,
  "local": "SÃO PAULO, SP",
  "data": "13/02/2021",
  "dezenas": [
    "11",
    "17",
    "25",
    "38",
    "52",
    "57"
  ],
  "premiacoes": [
    {
      "nome": "Sena",
      "acertos": 6,
      "vencedores": 0,
      "premio": "0,00"
    },
    {
      "nome": "Quina",
      "acertos": 5,
      "vencedores": 59,
      "premio": "42.795,90"
    },
    {
      "nome": "Quadra",
      "acertos": 4,
      "vencedores": 4548,
      "premio": "793,11"
    }
  ],
  "cidadeVencedor": "",
  "valorArrecadado": "43.793.878,50",
  "valorEstimado": "29.000.000,00",
  "valorAcumulado": "7.345.762,60",
  "acumulado": "SIM",
  "sorteioEspecial": "SIM",
  "observacoes": ""
}
```

* **Resultado Específico**

Lotofácil: https://loterias-api-gutotech.herokuapp.com/api/v0/lotofacil/2151
```
{
  "concurso": 2151,
  "data": "05/02/2021",
  "dezenas": [
    "01",
    "03",
    "06",
    "07",
    "08",
    "09",
    "11",
    "12",
    "13",
    "14",
    "15",
    "18",
    "21",
    "22",
    "24"
  ],
  "premiacoes": [
    {
      "nome": "15 Acertos",
      "acertos": 15,
      "vencedores": 1,
      "premio": "1.837.096,27"
    },
    {
      "nome": "14 Acertos",
      "acertos": 14,
      "vencedores": 180,
      "premio": "2.139,99"
    },
    {
      "nome": "13 Acertos",
      "acertos": 13,
      "vencedores": 7358,
      "premio": "25,00"
    },
    {
      "nome": "12 Acertos",
      "acertos": 12,
      "vencedores": 109252,
      "premio": "10,00"
    },
    {
      "nome": "11 Acertos",
      "acertos": 11,
      "vencedores": 684383,
      "premio": "5,00"
    }
  ],
  "acumulado15": "0,00",
  "estimativaPremio": "1.500.000,00",
  "valorAcumuladoEspecial": "43.328.848,88",
  "arrecadacaoTotal": null,
  "cidadeVencedor": ""
}
```

## Documentação da API
 
Para mais informações sobre todas as operações da API acesse: 

https://loterias-api-gutotech.herokuapp.com/swagger-ui.html
