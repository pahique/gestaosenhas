import React from 'react';
import Senha from '../components/Senha';

const PainelSenha = (props) => {

    return (
        <section className="App-section">
            <Senha titulo="Senha chamada" color="orangered" senha={props.senha} />
        </section>
    );
}

export default PainelSenha;
