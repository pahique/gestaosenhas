import React from 'react';
import Senha from '../components/Senha';

const Cliente = (props) => {

    return (
        <section className="App-section">
            <h1>Cliente</h1>
            <div className="button-section">
                <button onClick={props.onEmitirPreferencial}>Emitir senha preferencial</button> 
                <button onClick={props.onEmitirNormal}>Emitir senha normal</button>
            </div>
            <Senha titulo="Senha emitida" color="darkslategray" senha={props.senha} />
        </section>
    );
}

export default Cliente;
