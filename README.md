# workshop-final UBUNTU 22.04
Projecte final curs Selenium 25-26
Josep Escoda Herrando

Comandes per executar la suite després de clonar el repositori:
 Eliminació de drivers obsolets Firefox:
   sudo rm /snap/bin/geckodriver
 
 Després de clonar el repositori, des de l'arrel, executar:
   mvn clean test

 Les evidències es capturen en forma de pantalla a /screenshots

 OPCIONS ESPECIALS:
   -Dbrowser={chrome | freofox | chromeheadless}
    En el meu cas per poder executar sobre Firefox he d'afegir la variable TMPDIR=$HOME al davant de la comanda
       TMPDIR=$HOME mvn clean test -Dtest=RedirectTest -Dbrowser=firefox

   - (Als exemples dels logins) -Duser=USUARI -Dpassword=PASSWORD (Per defecte tomsmith / SuperSecretPAssword!)

   - (Exemple slider) -Dvalor=4.5

ATENCIÓ: He intentat fer una prova amb els formularis de Google i no ha estat possible localitzar els elements, ni enviant les dades per URL. Es pot veure a la classe FormulariTest.javaERROR. Finalment, l'exemple del formulari és idèntic als dels logins, així que ja el dono per fet.
