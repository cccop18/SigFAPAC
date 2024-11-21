/* ==================== CARREGAMENTO DE PAÍSES ==================== */

// Função para carregar países em um select específico
async function fetchCountries(selectId) {
    const response = await fetch('https://restcountries.com/v3.1/all');
    const countries = await response.json();
    const paisSelect = document.getElementById(selectId);

    // Ordena os países por nome
    countries.sort((a, b) => a.name.common.localeCompare(b.name.common));

    // Adiciona os países ao select
    countries.forEach(country => {
        const option = document.createElement('option');
        option.value = country.cca2; // Usa o código do país (como 'BR' para Brasil)
        option.textContent = country.name.common;
        paisSelect.appendChild(option);
    });
}

// Carregar países nos selects de residência e profissional
fetchCountries('pais');
fetchCountries('pais-prof');


/* ==================== FORMULÁRIO: CAMPOS BRASILEIROS E ESTRANGEIROS ==================== */

// Função para alternar campos brasileiros/estrangeiros
function toggleFields(paisSelectId, camposBrasileiroClass, camposEstrangeiroClass) {
    const paisSelect = document.getElementById(paisSelectId);
    const camposBrasileiro = document.querySelectorAll(`.${camposBrasileiroClass}`);
    const camposEstrangeiro = document.querySelectorAll(`.${camposEstrangeiroClass}`);

    paisSelect.addEventListener('change', function () {
        if (paisSelect.value === 'BR') {
            camposBrasileiro.forEach(campo => {
                campo.classList.remove('desativar');
                campo.required = true;
            });

            camposEstrangeiro.forEach(campo => {
                campo.classList.add('desativar');
                campo.required = false;
            });
        } else {
            camposBrasileiro.forEach(campo => {
                campo.classList.add('desativar');
                campo.required = false;
            });

            camposEstrangeiro.forEach(campo => {
                campo.classList.remove('desativar');
                campo.required = true;
            });
        }
    });
}

// Inicializa a alternância de campos para residencial e profissional
document.addEventListener('DOMContentLoaded', function () {
    toggleFields('pais', 'brasileiro-endR', 'estrangeiro-endR');
    toggleFields('pais-prof', 'brasileiro-endP', 'estrangeiro-endP');
});
