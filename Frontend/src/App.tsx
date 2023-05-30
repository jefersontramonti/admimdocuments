import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import DeleteUserForm from './components/DeleteUserForm/DeleteUserForm';
import UseForm from './components/UserForm/UserForm';
import UpdateUserForm from './components/UpdateUserForm/UpdateUserForm';
import SignIn from "./components/SignIn/SignIn";
import MiniDrawer from "./components/DenseAppBar/MiniDrawer";


function App() {
    return (
        <Router>
            <MiniDrawer/>
            <Routes>
                <Route path="/SignIn" element={<SignIn/>} />
                <Route path="/Novo Usuário" element={<UseForm />} />
                <Route path="/Deletar Usuário" element={<DeleteUserForm />} />
                <Route path="/Atualizar Usuário" element={<UpdateUserForm />} />
            </Routes>
        </Router>
    );
}

export default App;
