let especialidadeIndex = 1;

const subAreas = {
    exatas: {
        'computacao': {
            'teoria_da_computacao': ['Autômatos', 'Linguagens Formais'],
            'algoritmos': ['Estruturas de Dados', 'Complexidade Computacional']
        },
        'fisica': {
            'mecanica': ['Mecânica Clássica', 'Mecânica Quântica'],
            'termodinamica': ['Termodinâmica Clássica', 'Termodinâmica Estatística']
        }
    },
    humanas: {
        'psicologia': {
            'psicologia_clinica': ['Psicanálise', 'Terapia Cognitivo-Comportamental'],
            'psicologia_experimental': ['Análise Comportamental', 'Neuropsicologia']
        },
        'sociologia': {
            'teoria_sociologica': ['Funcionalismo', 'Marxismo'],
            'pesquisa_sociologica': ['Métodos Qualitativos', 'Métodos Quantitativos']
        }
    }
};

function attachEventListeners(index) {
    const areaConhecimentoSelect = document.getElementById(`areaConhecimento${index}`);
    const subArea1CSelect = document.getElementById(`subArea1C${index}`);
    const subArea2CSelect = document.getElementById(`subArea2C${index}`);
    const subArea3CSelect = document.getElementById(`subArea3C${index}`);

    function clearOptions(selectElement) {
        selectElement.innerHTML = '<option value="">Escolher</option>';
    }

    areaConhecimentoSelect.addEventListener('change', function () {
        const areaValue = this.value;
        clearOptions(subArea1CSelect);
        clearOptions(subArea2CSelect);
        clearOptions(subArea3CSelect);
        subArea1CSelect.disabled = true;
        subArea2CSelect.disabled = true;
        subArea3CSelect.disabled = true;

        if (areaValue) {
            subArea1CSelect.disabled = false;
            Object.keys(subAreas[areaValue]).forEach(subArea1 => {
                const option = document.createElement('option');
                option.value = subArea1;
                option.textContent = subArea1.replace('_', ' ');
                subArea1CSelect.appendChild(option);
            });
        }
    });

    subArea1CSelect.addEventListener('change', function () {
        const subArea1Value = this.value;
        const areaValue = areaConhecimentoSelect.value;
        clearOptions(subArea2CSelect);
        clearOptions(subArea3CSelect);
        subArea2CSelect.disabled = true;
        subArea3CSelect.disabled = true;

        if (subArea1Value) {
            subArea2CSelect.disabled = false;
            Object.keys(subAreas[areaValue][subArea1Value]).forEach(subArea2 => {
                const option = document.createElement('option');
                option.value = subArea2;
                option.textContent = subArea2.replace('_', ' ');
                subArea2CSelect.appendChild(option);
            });
        }
    });

    subArea2CSelect.addEventListener('change', function () {
        const subArea2Value = this.value;
        const subArea1Value = subArea1CSelect.value;
        const areaValue = areaConhecimentoSelect.value;
        clearOptions(subArea3CSelect);
        subArea3CSelect.disabled = true;

        if (subArea2Value) {
            subArea3CSelect.disabled = false;
            subAreas[areaValue][subArea1Value][subArea2Value].forEach(subArea3 => {
                const option = document.createElement('option');
                option.value = subArea3;
                option.textContent = subArea3;
                subArea3CSelect.appendChild(option);
            });
        }
    });
}

// Inicia com o primeiro conjunto de seletores
attachEventListeners(0);

// Previne o submit do form quando o botão adicionar for clicado
document.getElementById('addEspecialidadeBtn').addEventListener('click', function (e) {
    e.preventDefault(); // Previne o envio do formulário

    const especialidadesContainer = document.querySelector('.dados.conhecimento'); // O container onde os selects estão

    // Cria os selects diretamente no container, sem criar uma nova div
    const areaConhecimentoSelect = document.createElement('select');
    areaConhecimentoSelect.id = `areaConhecimento${especialidadeIndex}`;
    areaConhecimentoSelect.name = `areaConhecimento${especialidadeIndex}`;
    areaConhecimentoSelect.classList.add('obrigatorio');
    areaConhecimentoSelect.required = true;
    areaConhecimentoSelect.innerHTML = `
        <option value="">Escolher</option>
        <option value="exatas">Ciências Exatas</option>
        <option value="humanas">Ciências Humanas</option>
    `;

    const subArea1CSelect = document.createElement('select');
    subArea1CSelect.id = `subArea1C${especialidadeIndex}`;
    subArea1CSelect.name = `subArea1C${especialidadeIndex}`;
    subArea1CSelect.classList.add('obrigatorio');
    subArea1CSelect.required = true;
    subArea1CSelect.disabled = true;
    subArea1CSelect.innerHTML = `<option value="">Escolher</option>`;

    const subArea2CSelect = document.createElement('select');
    subArea2CSelect.id = `subArea2C${especialidadeIndex}`;
    subArea2CSelect.name = `subArea2C${especialidadeIndex}`;
    subArea2CSelect.classList.add('obrigatorio');
    subArea2CSelect.required = true;
    subArea2CSelect.disabled = true;
    subArea2CSelect.innerHTML = `<option value="">Escolher</option>`;

    const subArea3CSelect = document.createElement('select');
    subArea3CSelect.id = `subArea3C${especialidadeIndex}`;
    subArea3CSelect.name = `subArea3C${especialidadeIndex}`;
    subArea3CSelect.classList.add('obrigatorio');
    subArea3CSelect.required = true;
    subArea3CSelect.disabled = true;
    subArea3CSelect.innerHTML = `<option value="">Escolher</option>`;

    // Adiciona os selects diretamente no container
    especialidadesContainer.appendChild(areaConhecimentoSelect);
    especialidadesContainer.appendChild(subArea1CSelect);
    especialidadesContainer.appendChild(subArea2CSelect);
    especialidadesContainer.appendChild(subArea3CSelect);

    // Adiciona os event listeners para os novos selects
    attachEventListeners(especialidadeIndex);
    especialidadeIndex++;
});
