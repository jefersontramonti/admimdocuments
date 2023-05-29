import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {TableContainer} from "@mui/material";

interface User {
    id?: number;
    name: string;
    username: string;
    password: string;
    audits?: Array<string>;
    userType: string;
}

interface UserDetailTableProps {
    user: User;
}

function UserDetailTable({user}: UserDetailTableProps) {
    return (
        <TableContainer component={Paper}>
            <Table sx={{minWidth: 650}} size="small" aria-label="a dense table">
                <TableBody>
                    <TableRow>
                        <TableCell component="th" scope="row">
                            Nome
                        </TableCell>
                        <TableCell>
                            {user.name}
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell component="th" scope="row">
                            Nome de usuário
                        </TableCell>
                        <TableCell>
                            {user.username}
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell component="th" scope="row">
                            Tipo de usuário
                        </TableCell>
                        <TableCell>
                            {user.userType}
                        </TableCell>
                    </TableRow>
                    {/* Adicione mais campos conforme necessário */}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default UserDetailTable;
