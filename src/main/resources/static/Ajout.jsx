import React, { useState} from 'react';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';

function Ajout() {
  {stateDeclarations}

  {effectDeclarations}

  return (
    <div>
      <p>
        <label htmlFor="username" className="w-6rem">
          marque
        </label>
        <InputText value={marque} onChange={(e) => setMarque(e.target.value)} placeholder="Enter a marque" />
        <Button label="Ajouter" icon="pi pi-plus" className="w-10rem mx-auto" onSubmit={handleSubmit}></Button>
      </p>
    </div>
  );
}

export default Ajout;
