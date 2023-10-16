ddocument.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const dataNascimentoInput = document.getElementById("data_nascimento");
    const erroDataNascimento = document.getElementById("erroDataNascimento");
    const cpfInput = document.getElementById("cpf");
    const erroCpf = document.getElementById("erroCpf");
    const telefoneInput = document.getElementById("telefone");
    const erroTelefone = document.getElementById("erroTelefone");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const dataNascimento = dataNascimentoInput.value;
        const cpf = cpfInput.value;
        const telefone = telefoneInput.value;

        // para validar a data de nascimento
        if (!validarDataNascimento(dataNascimento)) {
            erroDataNascimento.textContent = "Data de nascimento inválida.";
            erroDataNascimento.style.color = "red";
        } else {
            erroDataNascimento.textContent = "";
        }

        // essa a de cpf
        if (!validarCpf(cpf)) {
            erroCpf.textContent = "CPF inválido.";
            erroCpf.style.color = "red";
        } else {
            erroCpf.textContent = "";
        }

        // aqui a de telefone
        if (!validarTelefone(telefone)) {
            erroTelefone.textContent = "Telefone inválido.";
            erroTelefone.style.color = "red";
        } else {
            erroTelefone.textContent = "";
        }
    });

    function validarDataNascimento(data) {
        const regexData = /^\d{2}\/\d{2}\/\d{4}$/;

        if (!regexData.test(data)) {
            return false;
        }

        const partesData = data.split('/');
        const dia = parseInt(partesData[0], 10);
        const mes = parseInt(partesData[1], 10);
        const ano = parseInt(partesData[2], 10);

        if (ano.toString().length !== 4 || mes < 1 || mes > 12 || dia < 1 || dia > 31) {
            return false;
        }

        const dataValida = new Date(ano, mes - 1, dia);

        if (
            isNaN(dataValida.getFullYear()) ||
            dataValida.getDate() !== dia ||
            dataValida.getMonth() !== mes - 1
        ) {
            return false;
        }

        return true;
    }

    function validarCpf(cpf) {
        return /^\d{11}$/.test(cpf);
    }

    function validarTelefone(telefone) {
        const regexTelefone = /^\d{9}$/;

        return regexTelefone.test(telefone);
    }
});
