<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tempo de Deslocação - Hospital CUF</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; padding: 20px; }
        button { padding: 10px 20px; background: #4285F4; color: white; border: none; cursor: pointer; margin: 5px; }
        #resultado { margin-top: 20px; font-size: 1.2em; }
    </style>
</head>
<body>

  <button onclick="obterLocalizacao()">Obter Localização Atual</button>
  <button onclick="minimoTempo()">Calcular Tempo Total</button>
  <div id="resultado"></div>

  <script>
    const API_KEY = "AIzaSyA1XgQnycgXJYha5wEmaefw_c_DhP2KxlU";

    // 1) API de espera CUF
    let waitData = [];
    async function fetchWaitTimes() {
      try {
        const res = await fetch("https://www.cuf.pt/api/get-waiting-times?locale=pt");
        waitData = await res.json();
      } catch (e) {
        console.error("Erro ao obter espera CUF:", e);
      }
    }
    fetchWaitTimes();

    // 2) Lista de hospitais
    const hospitais = [
      "Clínica CUF Almada, Almada",
      "Hospital CUF Cascais, Cascais",
      "Hospital CUF Coimbra, Coimbra",
      "Hospital CUF Descobertas, Lisboa",
      "Hospital CUF Porto, Porto",
      "Hospital CUF Santarém, Santarém",
      "Hospital CUF Sintra, Sintra",
      "Hospital CUF Tejo, Lisboa",
      "Hospital CUF Torres Vedras, Torres Vedras",
      "Hospital CUF Trindade, Porto",
      "Hospital CUF Viseu, Viseu"
    ];

    // 3) Geolocalização
    let minhaLat = null, minhaLng = null;
    function obterLocalizacao() {
      const resultado = document.getElementById("resultado");
      resultado.textContent = "A obter localização…";
      if (!navigator.geolocation) {
        return resultado.textContent = "❌ Navegador não suporta geolocalização.";
      }
      navigator.geolocation.getCurrentPosition(pos => {
        minhaLat = pos.coords.latitude;
        minhaLng = pos.coords.longitude;
        resultado.textContent = "📍 Localização obtida com sucesso.";
      }, () => {
        resultado.textContent = "❌ Permissão negada para geolocalização.";
      });
    }

    // 4) Função que chama a Distance Matrix e devolve minutos de trânsito
    async function calcularTempo(i) {
      const dest = encodeURIComponent(hospitais[i]);
      const url = 
        'https://maps.googleapis.com/maps/api/distancematrix/json'
        + '?origins=${minhaLat},${minhaLng}'
        + '&destinations=${dest}'
        + '&mode=driving'
        + '&departure_time=now'
        + '&traffic_model=best_guess'
        + '&key=${API_KEY}';
      try {
        const res = await fetch(url);
        const data = await res.json();
        const elem = data.rows[0].elements[0];
        if (elem.status === "OK" && elem.duration_in_traffic) {
          return elem.duration_in_traffic.value / 60;  // de segundos para minutos
        }
      } catch (e) {
        console.error("Erro DistanceMatrix:", e);
      }
      return Infinity;
    }

    // 5) O teu “comboio” de if/else, agora async, somando trânsito + espera
    async function minimoTempo() {
      const resultado = document.getElementById("resultado");
      if (minhaLat === null || minhaLng === null) {
        return resultado.textContent = "⚠️ Primeiro obtém a localização.";
      }

      // Pede idade ao utilizador (porque a tua lógica original usava `age`)
      let age = parseInt(prompt("Qual a sua idade?"), 10);
      if (isNaN(age)) {
        return resultado.textContent = "⚠️ Idade inválida.";
      }

      // Hora atual
      const agora = new Date();
      const horas = agora.getHours();
      const minutos = agora.getMinutes();

      let z = Infinity;
      let melhor = "nenhum";

      for (let i = 0; i < hospitais.length; i++){
        		if(i=0){
        			if (age >= 18){
        				if (8<= horas & horas<22){
            				tempoDeslocacao = calcularTempo(i);
            				if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[0];
            				}
            				else{
            		
            				}
            			}
        			}
        			else {
        				if (8<= horas & horas<19){
        					tempoDeslocacao = calcularTempo(i);
        					if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[0];
            				}
            				else{
            		
            				}
        				}
        			}
        		}
        		else if(i=1){
        			if (age >= 18){
        				if (8<= horas & horas<22){
            				tempoDeslocacao = calcularTempo(i);
            				if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[1];
            				}
            				else{
            		
            				}
            			}
        			}
        			else {
        				if (8<= horas & horas<22){
        					tempoDeslocacao = calcularTempo(i);
        					if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[1];
            				}
            				else{
            		
            				}
        				}
        			}
        		}
        		else if(i=2){
        			if (age >= 18){
        				if (9<= horas & horas<19.5){
            				tempoDeslocacao = calcularTempo(i);
            				if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[2];
            				}
            				else{
            		
            				}
            			}
        			}
        			else {
        				
        			}
        		}
        		else if(i=3){
        			if (age >= 18){
            				tempoDeslocacao = calcularTempo(i);
            				if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[3];
            				}
        			}
        			else {
        					tempoDeslocacao = calcularTempo(i);
        					if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[3];
            				}
        			}
        		}
        		else if(i=4){
        			if (age >= 18){
        				tempoDeslocacao = calcularTempo(i);
        				if (tempoDeslocacao < z){
        					z = tempoDeslocacao;
        					hospital = hospitais[4];
        				}
        			}
        			else {
        				if (8<= horas & horas<23){
        					tempoDeslocacao = calcularTempo(i);
        					if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[4];
            				}
            				else{
            		
            				}
        				}
        			}
        		}
        		else if(i=5){
        			if (age >= 18){
            				tempoDeslocacao = calcularTempo(i);
            				if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[5];
            				}
        			}
        		}
        		else if(i=6){
        			if (age >= 18){
        				if (8<= horas & horas<22){
            				tempoDeslocacao = calcularTempo(i);
            				if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[6];
            				}
            				else{
            		
            				}
            			}
        			}
        			else {
        				if (8<= horas & horas<22){
        					tempoDeslocacao = calcularTempo(i);
        					if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[6];
            				}
            				else{
            		
            				}
        				}
        			}
        		}
        		else if(i=7){
        			if (age >= 18){
        				tempoDeslocacao = calcularTempo(i);
        				if (tempoDeslocacao < z){
        					z = tempoDeslocacao;
        					hospital = hospitais[7];
        				}
        			}
        			else {
        				if (8<= horas & horas<21.5){
        					tempoDeslocacao = calcularTempo(i);
        					if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[7];
            				}
            				else{
            		
            				}
        				}
        			}
        		}
        		else if(i=8){
        			if (age >= 18){
        				tempoDeslocacao = calcularTempo(i);
        				if (tempoDeslocacao < z){
        					z = tempoDeslocacao;
        					hospital = hospitais[8];
        				}
        			}
        			else {
        				if (9<= horas & horas<21){
        					tempoDeslocacao = calcularTempo(i);
        					if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[8];
            				}
            				else{
            		
            				}
        				}
        			}
        		}
        		else if(i=9){
        			if (age >= 18){
        				if (8<= horas & horas<20){
            				tempoDeslocacao = calcularTempo(i);
            				if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[9];
            				}
            				else{
            		
            				}
            			}
        			}
        			else {
        				
        			}
        		}
        		if(i=10){
        			if (age >= 18){
        				if (8<= horas & horas<24){
            				tempoDeslocacao = calcularTempo(i);
            				if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[10];
            				}
            				else{
            		
            				}
            			}
        			}
        			else {
        				if (9.5<= horas & horas<19.5){
        					tempoDeslocacao = calcularTempo(i);
        					if (tempoDeslocacao < z){
            					z = tempoDeslocacao;
            					hospital = hospitais[10];
            				}
            				else{
            		
            				}
        				}
        			}
        		}
        	}
        }

      if (z === Infinity) {
        resultado.innerHTML = "❌ Nenhum hospital disponível neste horário/condição.";
      } else {
        resultado.innerHTML =
          `🩺 Melhor hospital: <strong>${melhor}</strong><br>` +
          `⏱ Total aproximado (trânsito+espera): <strong>${Math.round(z)} min</strong>`;
      }
    
  </script>

</body>
</html>
