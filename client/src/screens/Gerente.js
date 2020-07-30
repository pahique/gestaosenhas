import React from 'react';

const Gerente = (props) => {

    return (
        <section className="App-section">
            <h1>Gerente</h1>
            <div className="button-section">
                <button onClick={props.onChamarProxima}>Chamar próxima senha</button>
                <button onClick={props.onZerarContadores}>Zerar contadores</button> 
            </div>
        </section>
    );
}

export default Gerente;

