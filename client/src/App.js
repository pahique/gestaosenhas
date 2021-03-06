import React, { useState, useEffect } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import SockJS from 'sockjs-client';
import StompJS from 'stompjs';

import PainelSenha from './screens/PainelSenha';
import Gerente from './screens/Gerente';
import Cliente from './screens/Cliente';

import './App.css';


function App() {

  const senhaVazia = '-----';
  const [senhaAtual, setSenhaAtual] = useState(senhaVazia); 
  const [senhaEmitida, setSenhaEmitida] = useState(senhaVazia); 
  const [error, setError] = useState('');

  useEffect(() => {
    let socket = new SockJS('http://localhost:8080/gestaosenhas/ws/');
    let stompClient = StompJS.over(socket);
    if (stompClient && !stompClient.connected) {
      stompClient.debug = () => {};
      stompClient.connect({}, () => {
        stompClient.subscribe('/topic/senha', (frame) => {
          let senha = (frame.body !== '' ? frame.body : senhaVazia);
          setSenhaAtual(senha);
          setError('');
        });
      });
    }

    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    };
    fetch('/gestaosenhas/api/senha/ultima', requestOptions)
      .then(response => response.json())
      .then(resposta => {
          //console.log(resposta);
          if (resposta.senha) {
            setSenhaAtual(resposta.senha.senhaFormatada);
            setError('');
          } else {
            setError(resposta.error);
          }
        }).catch(error => {
          console.log(error);
          setError("Erro ao obter ultima senha chamada");
        });
  }, []);
  
  const handleChamarProximaSenha = () => {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: ''
    };
    fetch('/gestaosenhas/api/admin/senha/proxima', requestOptions)
      .then(response => response.json())
      .then(resposta => {
          //console.log(resposta);
          if (resposta.senha) {
            setSenhaAtual(resposta.senha.senhaFormatada);
            setError('');
          } else {
            setSenhaAtual(senhaVazia);
            setError(resposta.error);
          }
        }).catch(error => {
          console.log(error);
          setError("Erro ao chamar proxima senha");
        });
  }

  const handlerZerarContadores = () => {
    const requestOptions = {
      method: 'DELETE',
      headers: { 'Content-Type': 'application/json' },
      body: '{}'
    };
    fetch('/gestaosenhas/api/admin/senha/reset', requestOptions)
      .then(response => response.json())
      .then(resposta => {
          //console.log(resposta);
          if (error) {
            setError(resposta.error);
          }
        }).catch(error => {
          console.log(error);
          setError("Erro ao zerar contadores");
        });
  }

  const handleNovaSenha= (tipoSenha) => {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ 'tipoSenha': tipoSenha})
    };
    fetch('/gestaosenhas/api/senha/nova', requestOptions)
      .then(response => response.json())
      .then(resposta => {
          //console.log(resposta);
          if (resposta.senha) {
            setSenhaEmitida(resposta.senha.senhaFormatada);
            setError('');
          } else {
            setError(resposta.error);
          }
        }).catch(error => {
          console.log(error);
          setError("Erro ao gerar nova senha");
        });
  }

  return (
    <div className="App">
      <header className="App-header">
        Gestão de Senhas de Atendimento
      </header>
      <main className="App-main">
        <BrowserRouter>
        <Switch>
          <Route exact path="/">
            <PainelSenha senha={senhaAtual} />
            <section className="App-messages">{error}</section>
            <Cliente onEmitirPreferencial={() => handleNovaSenha(1)} onEmitirNormal={() => handleNovaSenha(2)} senha={senhaEmitida} />            
          </Route>
          <Route exact path="/gerente">
            <PainelSenha senha={senhaAtual} />
            <section className="App-messages">{error}</section>
            <Gerente onChamarProxima={handleChamarProximaSenha} onZerarContadores={handlerZerarContadores} />            
          </Route>
        </Switch>
        </BrowserRouter>
      </main>
    </div>
  );
}

export default App;
