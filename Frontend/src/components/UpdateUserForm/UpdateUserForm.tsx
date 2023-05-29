import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import {getUsers, getUserById} from '../../services/apiService'


interface User {
    id?: number;
    name: string;
    username: string;
    password: string;
    audits?: Array<string>;
    userType: string;
}

const UpdateUserForm: React.FC = () => {
    const [users, setUsers] = useState<User[]>([]);
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const [selectedUserId, setSelectedUserId] = useState<string>('');
    const [user, setUser] = useState<User | null>(null);
    const [alertMessage, setAlertMessage] = useState<string | null>(null);
    const [alertSeverity, setAlertSeverity] = useState<'error' | 'warning' | 'info' | 'success' | null>(null);
    const [isLoading, setIsLoading] = useState(false);

    const showAlertMessage = (message: string, severity: 'error' | 'warning' | 'info' | 'success') => {
        setAlertMessage(message);
        setAlertSeverity(severity);

        setTimeout(() => {
            setAlertMessage(null);
            setAlertSeverity(null);
        }, 1400);
    };
    const handleUserChange = async (event: React.ChangeEvent<HTMLSelectElement>) => {
        const id = event.target.value;
        setSelectedUserId(id);
        setAlertMessage(null);

        if (id) {
            setIsLoading(true)
            try {
                const response = await getUserById(id);
                setUser(response.data);
            } catch (error) {
                console.error(error);
            } finally {
                setIsLoading(false);
            }
        } else {
            setUser(null);
        }
    };

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        if (user) {
            let newUser = {...user, [event.target.name]: event.target.value};

            if (event.target.name === "userType") {
                newUser.password = event.target.value === "employee" ? "1234" : "";
                newUser.username = event.target.value === "employee" ? "galvanotek" : "";
            }

            setUser(newUser);
        }
    };

    const handleUpdateSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        if (user && user.id) {
            try {
                await axios.put(`/api/users/${user.id}`, user);
                showAlertMessage('Usuário atualizado com sucesso!!!', 'success');
                setUser(null);
                setSelectedUserId('');
                fetchUsers();
            } catch (error) {
                console.error(error);
                showAlertMessage('Usuário não atualizado', 'error');
            }
        } else {
            showAlertMessage('Usuário não selecionado para atualização', 'error');
        }
    };

    const fetchUsers = async () => {
        setIsLoading(true);
        try {
            const response = await getUsers();
            setUsers(response.data);
        } catch (error) {
            console.error(error);
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    return (
        <Box maxWidth="600px" mx="auto" p={2}>
            <div>
                <h2>Atualizar Usuário</h2>
                <select
                    onChange={handleUserChange}
                    style={{width: '103%', padding: '8px', marginBottom: '10px'}}
                >
                    <option value="">Selecione um usuário</option>
                    {users.map(user => (
                        <option key={user.id} value={user.id}>{user.name}</option>
                    ))}
                </select>

                {user && (
                    <form onSubmit={handleUpdateSubmit}>
                        <div style={{marginBottom: '10px'}}>
                            <input
                                type="text"
                                name="name"
                                value={user.name}
                                onChange={handleInputChange}
                                placeholder="Nome"
                                required
                                style={{width: '100%', padding: '8px'}}
                            />
                        </div>
                        <div style={{marginBottom: '10px'}}>
                            <select
                                name="userType"
                                value={user.userType}
                                onChange={handleInputChange}
                                required
                                style={{width: '103%', padding: '8px'}}
                            >
                                <option value="">Selecione o tipo de usuário</option>
                                <option value="employee">Funcionário</option>
                                <option value="coordinator">Coordenador</option>
                                <option value="document_editor">Editor de Documentos</option>
                                <option value="document_approver">Aprovador de Documentos</option>
                            </select>
                        </div>
                        <div style={{marginBottom: '10px'}}>
                            <input
                                type="text"
                                name="username"
                                value={user.userType === "employee" ? "galvanotek" : user.username}
                                onChange={handleInputChange}
                                placeholder="Nome de usuário"
                                disabled={user.userType === "employee"}
                                required
                                style={{width: '100%', padding: '8px'}}
                            />
                        </div>
                        <div style={{marginBottom: '10px'}}>
                            <input
                                type="password"
                                name="password"
                                value={user.userType === "employee" ? "1234" : user.password}
                                onChange={handleInputChange}
                                placeholder="Senha"
                                disabled={user.userType === "employee"}
                                required
                                style={{width: '100%', padding: '8px'}}
                            />
                        </div>
                        <div style={{display: 'flex', justifyContent: 'center'}}>
                            <Button variant="contained" type="submit" disabled={isLoading}>
                                Atualizar
                            </Button>
                        </div>
                    </form>
                )}

                {alertMessage && alertSeverity && (
                    <Alert variant="filled" severity={alertSeverity}>
                        {alertMessage}
                    </Alert>
                )}
            </div>
        </Box>
    );
};

export default UpdateUserForm;


