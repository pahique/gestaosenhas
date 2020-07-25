import React, { useState, useEffect } from 'react';
import './App.css';

function App() {

  const [senhaAtual, setSenhaAtual] = useState('-----'); 
  const [senhaEmitida, setSenhaEmitida] = useState('-----'); 
  const [error, setError] = useState('');

  useEffect(() => {
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
    fetch('/gestaosenhas/api/senha/proxima', requestOptions)
      .then(response => response.json())
      .then(resposta => {
          //console.log(resposta);
          if (resposta.senha) {
            setSenhaAtual(resposta.senha.senhaFormatada);
            setError('');
          } else {
            setSenhaAtual('-----');
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
    fetch('/gestaosenhas/api/senha/reset', requestOptions)
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
        <section className="painel-senha chamada">
          <fieldset>
            <legend>Senha chamada</legend>
            {senhaAtual}
          </fieldset>
        </section>
        <section className="App-messages">
        {error}
        </section>
        <section className="App-section">
          <h1>Gerente</h1>
          <div className="button-section">
            <button onClick={handleChamarProximaSenha}>Chamar próxima senha</button>
            <button onClick={handlerZerarContadores}>Zerar contadores</button> 
          </div>
        </section>
        <section className="App-section">
          <h1>Público</h1>
          <div className="button-section">
            <button onClick={() => handleNovaSenha(1)}>Emitir senha preferencial</button> 
            <button onClick={() => handleNovaSenha(2)}>Emitir senha normal</button>
          </div>
          <section className="painel-senha emitida">
            <fieldset>
              <legend>Senha emitida</legend>
              {senhaEmitida}
            </fieldset>
          </section>
        </section>
      </main>
    </div>
  );
}

export default App;
