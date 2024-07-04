import DoubleList from "@/components/doubleList/doubleList"
import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar";
import {Button} from "@/components/ui/button";
import {SettingsIcon} from "lucide-react";

const MyPage = () => {
    return (
        <div className={'flex w-full justify-between items-center h-full'}>
            <Avatar className={'w-[128px] h-[128px] flex justify-center items-center'}>
                <AvatarImage src="https://github.com/shadcn.png" alt="@shadcn"/>
                <AvatarFallback>CN</AvatarFallback>
            </Avatar>
            <div className={'flex justify-around w-full'}>
                <DoubleList listData={[
                    {
                        label: "Фамлия имя отчество",
                        content: "Пуньк среньк"
                    },
                    {
                        label: "Почта",
                        content: "ilublukontour228@mail.ru"
                    }
                    ,
                    {
                        label: "Логин",
                        content: "ilublukontour228"
                    }
                ]}
                />
                <DoubleList listData={[
                    {
                        label: "Номер ВУ",
                        content: "12321321312"
                    },
                    {
                        label: "Телеграмм",
                        content: "https://t.me/ilublukontour228"
                    }
                    ,
                    {
                        label: "Телефон",
                        content: "+7 (960) 558-78-77"
                    }
                ]}
                />
            </div>
            <div className={'h-full flex items-start'}>
                <Button size={'icon'} variant={'outline'}>
                    <SettingsIcon/>
                </Button>
            </div>
        </div>
    )
}

export default MyPage