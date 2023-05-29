import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ResponsiveAppBar from './components/ResponsiveAppBar/ResponsiveAppBar';
import DeleteUserForm from './components/DeleteUserForm/DeleteUserForm';
import UseForm from './components/UserForm/UserForm';
import UpdateUserForm from './components/UpdateUserForm/UpdateUserForm';

function App() {
    return (
        <Router>
            <ResponsiveAppBar/>
            <Routes>
                <Route path="/Novo Usuário" element={<UseForm />} />
                <Route path="/Deletar Usuário" element={<DeleteUserForm />} />
                <Route path="/Atualizar Usuário" element={<UpdateUserForm />} />
            </Routes>
        </Router>
    );
}

export default App;
