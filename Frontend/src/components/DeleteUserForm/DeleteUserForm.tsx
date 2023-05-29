import React, {useEffect, useState} from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Select, {SelectChangeEvent} from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import UserDetailTable from "../table/UserDetailTable";
import { getUsers, getUserById, deleteUserById } from '../../services/apiService'

interface User {
    id?: number;
    name: string;
    username: string;
    password: string;
    audits?: Array<string>;
    userType: string;
}

const DeleteUserForm: React.FC = () => {
    const [users, setUsers] = useState<User[]>([]);
    const [selectedUserId, setSelectedUserId] = useState<number | null>(null);
    const [selectedUser, setSelectedUser] = useState<User | null>(null);
    const [alertMessage, setAlertMessage] = useState<string | null>(null);
    const [alertSeverity, setAlertSeverity] = useState<'error' | 'warning' | 'info' | 'success' | null>(null);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        setIsLoading(true); // Iniciar o carregamento
        try {
            const response = await getUsers();
            setUsers(response.data);
        } catch (error) {
            console.error(error);
            showAlertMessage('Não foi possivel obter a lista de usuários', 'error');
        } finally {
            setIsLoading(false); // Finalizar o carregamento
        }
    };

    const fetchUserDetails = async (id: number) => {
        setIsLoading(true); // Iniciar o carregamento
        try {
            const response = await getUserById(id.toString());
            setSelectedUser(response.data);
        } catch (error) {
            console.error(error);
            showAlertMessage('Não foi possivel obter os detalhes do usuário', 'error');
        } finally {
            setIsLoading(false); // Finalizar o carregamento
        }
    };

    const showAlertMessage = (message: string, severity: 'error' | 'warning' | 'info' | 'success') => {
        setAlertMessage(message);
        setAlertSeverity(severity);

        setTimeout(() => {
            setAlertMessage(null);
            setAlertSeverity(null);
        }, 1500);
    };
    const handleUserChange = (
        event: SelectChangeEvent<string>,
    ) => {
        const id = event.target.value === "" ? null : Number(event.target.value);
        setSelectedUserId(id);
        if (id !== null) {
            fetchUserDetails(id);
        }
    };


    const handleDelete = async () => {
        if (selectedUserId === null) {
            showAlertMessage('Nenhum usuário selecionado para exclusão', 'error');
            return;
        }

        setIsLoading(true); // Iniciar o carregamento
        try {
            await deleteUserById(selectedUserId.toString());
            showAlertMessage('Usuário excluído com sucesso', 'success');
            fetchUsers();
            setSelectedUser(null);
        } catch (error) {
            console.error(error);
            showAlertMessage('Não foi possível excluir o usuário', 'error');
        } finally {
            setIsLoading(false); // Finalizar o carregamento
        }
    };



    return (
        <Box maxWidth="600px" mx="auto" p={2}>
            <h2>Excluir Usuário</h2>

            <Select
                value={selectedUserId !== null ? selectedUserId.toString() : ''}
                onChange={handleUserChange}
                fullWidth
                sx={{ height: 32 }}
            >
                <MenuItem value="">Selecione um usuário</MenuItem>
                {users.map((user) => (
                    <MenuItem key={user.id} value={user.id}>
                        {user.name}
                    </MenuItem>
                ))}
            </Select>


            {selectedUser && (
                <Box mt={2}>
                    <h3>Detalhes do Usuário</h3>
                    <UserDetailTable user={selectedUser} />
                </Box>
            )}

            <Box mt={2} display="flex" justifyContent="center">
                <Stack spacing={2} direction="row">
                    <Button variant="contained" onClick={handleDelete} disabled={isLoading}>
                        Excluir
                    </Button>
                </Stack>
            </Box>
            <Box padding={'50px'}>
                {alertMessage && alertSeverity && (
                    <Alert variant="filled" severity={alertSeverity}>
                        {alertMessage}
                    </Alert>
                )}
            </Box>
        </Box>
    );
};

export default DeleteUserForm;
