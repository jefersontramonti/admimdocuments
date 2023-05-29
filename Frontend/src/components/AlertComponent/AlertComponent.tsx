import React from 'react';
import Alert from '@mui/material/Alert';
import {Box} from "@mui/material";

interface AlertProps {
    message: string | null;
    severity: 'error' | 'warning' | 'info' | 'success' | null;
}

const AlertComponent: React.FC<AlertProps> = ({message, severity}) => {
    if (!message || !severity) {
        return null;
    }

    return (
        <Box padding={5}>
            <Alert variant="filled" severity={severity}>
                {message}
            </Alert>
        </Box>

    );
};

export default AlertComponent;
