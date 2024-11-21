/* ==================== CARREGAMENTO DE ESTADOS E MUNICÍPIOS ==================== */

// Carregamento de estados do IBGE para selects com a classe 'uf'
document.addEventListener('DOMContentLoaded', async function() {
    const estadosSelects = document.querySelectorAll('.uf'); 

    try {
        const response = await fetch('https://servicodados.ibge.gov.br/api/v1/localidades/estados');
        const estados = await response.json();

        // Ordena os estados por nome
        estados.sort((a, b) => a.nome.localeCompare(b.nome));

        // Adiciona os estados a todos os selects com a classe 'uf'
        estados.forEach(estado => {
            const option = document.createElement('option');
            option.value = estado.id;  // ID do estado, que será usado para carregar os municípios
            option.textContent = estado.nome;

            estadosSelects.forEach(select => {
                select.appendChild(option.cloneNode(true)); 
            });
        });

    } catch (error) {
        console.error('Erro ao carregar os estados:', error);
    }
});

// Função para carregar municípios de acordo com o estado selecionado
async function carregarMunicipios(estadoId, municipioSelectId) {
    const municipioSelect = document.getElementById(municipioSelectId);

    // Reseta o select de municípios
    municipioSelect.innerHTML = '<option value="">Selecione o Município</option>';

    if (estadoId) {
        try {
            const response = await fetch(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${estadoId}/municipios`);
            const municipios = await response.json();

            // Adiciona os municípios ao select correspondente
            municipios.forEach(municipio => {
                const option = document.createElement('option');
                option.value = municipio.id;
                option.textContent = municipio.nome;
                municipioSelect.appendChild(option);
            });

        } catch (error) {
            console.error('Erro ao carregar os municípios:', error);
        }
    }
}

// Listeners para mudança de estado nos selects 'uf1' e 'uf2'
document.getElementById('uf1').addEventListener('change', function() {
    const estadoId = this.value;
    carregarMunicipios(estadoId, 'municipio1');  // Carrega os municípios no select correspondente
});

document.getElementById('uf2').addEventListener('change', function() {
    const estadoId = this.value;
    carregarMunicipios(estadoId, 'municipio2');  // Carrega os municípios no select correspondente
});
