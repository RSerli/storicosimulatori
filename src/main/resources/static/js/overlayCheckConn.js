document.addEventListener('DOMContentLoaded', function () {
    const overlay = document.getElementById('overlayCheckConnessioni');
    const checkLink = document.getElementById('btnCheckConnessioni');

    if (checkLink && overlay) {
        checkLink.addEventListener('click', function (event) {
            event.preventDefault();
            overlay.style.display = 'flex';

            fetch(this.href, {
                method: 'GET',
                credentials: 'same-origin'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Errore durante il controllo delle connessioni');
                    }
                    window.location.reload();
                })
                .catch(() => {
                    overlay.style.display = 'none';
                    alert('Impossibile aggiornare lo stato delle connessioni. Riprova.');
                });
        });
    }
});