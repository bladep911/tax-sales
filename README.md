Nella stesura del progetto ho utilizzato principalmente i seguenti patter

1. Strategy pattern per isolare la logica di calcolo in base al tipo di prodotto
L'interfaccia e le relative classi che implementano il pattern sono
- IProductTaxCalculator (package com.lsct.edp.business): 
  interfaccia che definisce la firma del metodo per calcolare le tassa da applicare ad un prodotto
- AProductTaxCalculator (package com.lsct.edp.business): 
  abstract class che implementa la logica generica indipendente dal prodotto
- BasicProductTaxCalculator (package com.lsct.edp.business): 
  classe che definisce la logica da applicare al calcolo delle tasse per i prodotti base
- ImportedProductTaxCalculator (package com.lsct.edp.business): 
  classe che definisce la logica da applicare al calcolo delle tasse per i prodotti importati

2. Factory patter per utilizzare la logica opportuna in base al tipo di prodotto
Implementato da
- ProductTaxCalculatorFactory (package com.lsct.edp.business) 
 
Il package com.lsct.edp.models contiene le classi 
- Product che rappresenta un generico prodotto
- PurchaseItem che rappresenta il prodotto acquistato e la quantita' acquistata
- Purcahse che rappresenta la lista dei prodotti acquistati

Resource files:
config.properties: contiene i settings del progetto

Test resource files:
assert1.properties: contiene i valori attesi del primo test  
assert2.properties: contiene i valori attesi del secondo test
assert3.properties: contiene i valori attesi del terzo test
logging.properties: logger settings
purchase1.json: valori di input del primo test
purchase2.json: valori di input del secondi test
purchase3.json: valori di input del terzo test

