import React, { useEffect, useState } from 'react';
import axios from 'axios';
import DoubleList from "@/components/doubleList/doubleList";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";

interface UserData {
    profileImageUrl: string;
    username: string;
    initials: string;
    lastname: string;
    firstname: string;
    email: string;
    login: string;
    role: string;
    dateOfBirth: string;
    phone: string;
}

const MyPage: React.FC = () => {
    const [userData, setUserData] = useState<UserData | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get<UserData>('/api/user/search');
                setUserData(response.data);
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };

        fetchData();
    }, []);

    if (!userData) {
        return <div>Loading...</div>;
    }

    return (
        <div className={'flex w-full relative'}>
            <Avatar className={'w-[128px] h-[128px] flex justify-center items-center'}>
                <AvatarImage src={userData.profileImageUrl} alt={userData.username}/>
                <AvatarFallback>{userData.initials}</AvatarFallback>
            </Avatar>
            <div className={'flex justify-around w-full'}>
                <DoubleList listData={[
                    {
                        label: "Фамилия",
                        content: userData.lastname
                    },
                    {
                        label: "Имя",
                        content: userData.firstname
                    },
                    {
                        label: "Почта",
                        content: userData.email
                    },
                    {
                        label: "Логин",
                        content: userData.login
                    }
                ]} />
                <DoubleList listData={[
                    {
                        label: "Роль",
                        content: userData.role
                    },
                    {
                        label: "Дата рождения",
                        content: userData.dateOfBirth
                    },
                    {
                        label: "Телефон",
                        content: userData.phone
                    }
                ]} />
            </div>
        </div>
    );
};

export default MyPage;