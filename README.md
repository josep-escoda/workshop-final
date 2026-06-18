# 🚀 Workshop Final: Automatització amb Selenium WebDriver

Projecte final del curs de Selenium (Promoció 2025-2026). Estructurat sota el patró de disseny **POM (Page Object Model)**, utilitzant **Java**, **TestNG**, **Maven** i executat sobre un entorn **Ubuntu 22.04 LTS**.

**Autor:** Josep Escoda Herrando  

---

## 🛠️ Requisits previs de l'entorn (Ubuntu)

A causa de la gestió de paquets Snap a Ubuntu, per garantir l'execució correcta del driver de Firefox (`geckodriver`), cal netejar possibles conflictes del sistema abans de començar:

sudo rm /snap/bin/geckodriver


## 🚀 Execució de la Suite de Tests

Després de clonar el repositori, situa't a l'arrel del projecte i executa la suite completa amb la següent comanda de Maven:
Bash

mvn clean test

## 📸 Captura d'Evidències

El framework inclou un sistema de Listeners personalitzat (TestListener.java). En cas que qualsevol test falli, es capturarà automàticament una captura de pantalla que es guardarà a la ruta:

📁 /screenshots

## ⚙️ Paràmetres i Opcions Especials

El framework és dinàmic i permet passar variables en temps d'execució des de la terminal amb el flag -D:

| Paràmetre | Descripció | Exemple / Valor per defecte |
| :--- | :--- | :--- |
| **`-Dbrowser`** | Selecció del navegador per a l'execució del test.<br>*(Opcions: `chrome`, `firefox` o `chromeheadless`)* | `chrome` |
| **`-Duser`** | Credencial d'accés per a las proves de Login. | `tomsmith` |
| **`-Dpassword`** | Contrasenya vinculada a l'usuari de les proves de Login. | `SuperSecretPassword!` |
| **`-Dvalor`** | Valor numèric objectiu on s'ha de desplaçar el component Slider. | `4.5` |


## 🦊 Nota important per a Firefox a Linux

A causa de les restriccions d'escriptura d'Snap a les carpetes temporals del sistema, per executar els tests sobre Firefox cal forçar la variable d'entorn TMPDIR apuntant al directori de l'usuari:

TMPDIR=$HOME mvn clean test -Dtest=RedirectTest -Dbrowser=firefox


## 📝 Notes del Desenvolupament i Arquitectura

    Gestió de Formularis Dinàmics: Durant el desenvolupament s'ha testejat l'automatització sobre la plataforma Google Forms (FormulariTest.javaERROR). A causa de l'ofuscació de codi, la generació dinàmica de components flotants fora de la vista i les contramesures antibot de Google, la interacció amb els elements va presentar problemes d'interactivitat (fins i tot aplicant Deep Linking per URL).

    Decisió de Disseny: Atès que la lògica de validació d'un formulari (interacció amb camps de text, enviament i asseveracions) és conceptualment idèntica a la dels casos de prova implementats a les pantalles de Login, es dóna per coberta aquesta competència en la suite principal de l'aplicació, garantint així l'estabilitat i robustesa del framework final de cara al lliurament.
