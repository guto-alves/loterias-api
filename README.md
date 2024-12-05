[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
![GitHub stars](https://img.shields.io/github/stars/guto-alves/loterias-api)
<a href="https://t.me/+VNG9nOMxG1NkMTEx">
<img src="https://img.shields.io/badge/Telegram-2CA5E0?style=for-the-badge&logo=telegram&logoColor=white" height="20" />
</a>

<p align="center">
  <img src="https://user-images.githubusercontent.com/48946749/147809259-e7b15a3b-2e90-42c2-abaf-a6cacdc77e03.png">
  <h2 align="center">API Loterias CAIXA</h2>
  <p align="center">
    API Gratuita de resultado de jogos das <a href="http://loterias.caixa.gov.br/wps/portal/loterias">Loterias CAIXA</a>.<br>
  </p>
</p>


## Doação (*Importante!*)

A API é 100% gratuita. Porém ela está hospedada no [Heroku](https://www.heroku.com/) que recentemente [parou de oferecer planos de produtos gratuitos](https://blog.heroku.com/next-chapter), portanto agora há custos para mantê-la.

Então se você achou este projeto interessante ou é útil para você de alguma forma, por favor considere fazer uma doação para ajudar com os custos da hospedagem, qualquer valor ajudará.

### 1. Doar pelo QR CODE PIX

**Escaneie** nosso QR CODE e doe qualquer valor!

<img src="https://github.com/user-attachments/assets/2b857f15-eb0f-4db0-a1dc-4f97b085c429" width="150" height="150">


### 2. Doar com PagBank

Utilize o link do PagBank para doar utilizando outras formas como: Cartão de Crédito, Débito e Boleto.

**Para doar copie e cole no seu navegador o seguinte link**: https://pag.ae/7_9FuVnxG

![Donate](https://stc.pagseguro.uol.com.br/public/img/botoes/doacoes/205x30-doar.gif) 

## Exemplos de Retorno
Atualmente o banco de dados contém os jogos das loterias ...

https://loteriascaixa-api.herokuapp.com/api

```
[
  "maismilionaria",
  "megasena",
  "lotofacil",
  "quina",
  "lotomania",
  "timemania",
  "duplasena",
  "federal",
  "diadesorte",
  "supersete"
]
```

### **Obtendo o Resultado Mais Recente**

URL BASE: ```https://loteriascaixa-api.herokuapp.com/api/<loteria>/latest```

Apenas substitua ```<loteria>``` pelo nome da loteria desejada. Por exemplo, consultando pelo resultado da Mega-Sena (```megasena```) mais recente: 

https://loteriascaixa-api.herokuapp.com/api/megasena/latest

```
{
  "loteria": "megasena",
  "concurso": 2620,
  "data": "12/08/2023",
  "local": "ESPAÇO DA SORTE em SÃO PAULO, SP",
  "dezenasOrdemSorteio": [
    "26",
    "21",
    "13",
    "04",
    "06",
    "28"
  ],
  "dezenas": [
    "04",
    "06",
    "13",
    "21",
    "26",
    "28"
  ],
  "trevos": [
    
  ],
  "timeCoracao": null,
  "mesSorte": null,
  "premiacoes": [
    {
      "descricao": "6 acertos",
      "faixa": 1,
      "ganhadores": 4,
      "valorPremio": 29058128.28
    },
    {
      "descricao": "5 acertos",
      "faixa": 2,
      "ganhadores": 404,
      "valorPremio": 23042.04
    },
    {
      "descricao": "4 acertos",
      "faixa": 3,
      "ganhadores": 21667,
      "valorPremio": 613.76
    }
  ],
  "estadosPremiados": [
    
  ],
  "observacao": "",
  "acumulou": false,
  "proximoConcurso": 2621,
  "dataProximoConcurso": "16/08/2023",
  "localGanhadores": [
    {
      "ganhadores": 1,
      "municipio": "CANAL ELETRONICO",
      "nomeFatansiaUL": "",
      "serie": "",
      "posicao": 1,
      "uf": "--"
    },
    {
      "ganhadores": 1,
      "municipio": "UBERABA",
      "nomeFatansiaUL": "",
      "serie": "",
      "posicao": 1,
      "uf": "MG"
    },
    {
      "ganhadores": 2,
      "municipio": "SINOP",
      "nomeFatansiaUL": "",
      "serie": "",
      "posicao": 1,
      "uf": "MT"
    }
  ],
  "valorArrecadado": 161458740,
  "valorAcumuladoConcurso_0_5": 10778824.03,
  "valorAcumuladoConcursoEspecial": 72866210.44,
  "valorAcumuladoProximoConcurso": 0.0,
  "valorEstimadoProximoConcurso": 3500000.0
}
```

-  **Observações**: Os campos <i><b>timeCoracao</b></i> e <i><b>mesSorte</b></i> só terão algum valor quando a loteria pesquisada for Timemania (timemania) ou Dia de Sorte (diadesorte) respectivamente.

## Documentação da API
 
Para mais informações sobre todas as operações da API acesse: 

https://loteriascaixa-api.herokuapp.com/swagger-ui/#/Loterias

![image](https://user-images.githubusercontent.com/48946749/144352143-7140d64d-43a9-465c-b12c-7d5d3514ccd5.png)

## Contribuição

Quaisquer contribuições para este repositório são bem-vindas! Em caso de problema, dúvida ou dificuldade não hesite em criar uma [Issue](https://github.com/guto-alves/loterias-api/issues).
