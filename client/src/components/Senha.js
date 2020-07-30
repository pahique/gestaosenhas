import React from 'react';
import './Senha.css';

const Senha = (props) => {
    const colorStyle = {
        color: props.color,
        borderColor: props.color
    };  
    return (
        <div className="painel-senha">
            <fieldset style={colorStyle}>
                <legend>{props.titulo}</legend>
                {props.senha}
            </fieldset>
        </div>
    );
}

export default Senha;