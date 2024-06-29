'use client'
import React, { useState } from 'react';
import { Button } from '@/components/ui/button';
import Link from 'next/link';

export const Header = () => {
  return (
    <header className="flex justify-between sticky w-full p-4">
      <div className="flex gap-5" style={styles.buttonsContainer}>
      <Logo />
      <Button className="bg-green-600" style={{ width: '180px', marginRight: '10px' }}>Главная</Button>
        <Profile />
          <Teaching />
          <Settings />
          <AboutUs />
          <Help />
        <ProfileImageButton />

      </div>
    </header>
  );
};
const Logo = () => {
  return (
    <div style={styles.logoContainer}>
          <img
            src="/развод лохов.png" // Путь к вашему логотипу
            alt="развод лохов"
            className="w-60 h-20"
          />
    </div>
  );
};

const Settings = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div 
      style={styles.dropdownContainer} 
      onMouseEnter={() => setIsOpen(true)} 
      onMouseLeave={() => setIsOpen(false)}
    >
      <button style={styles.mainButton}>Настройки</button>
      {isOpen && (
        <div style={styles.dropdownContent}>
          <button style={styles.dropdownButton}>Настройки профиля</button>
          <button style={styles.dropdownButton}>Уведомления</button>
        </div>
      )}
    </div>
  );
};

const styles: { [key: string]: React.CSSProperties } = {
  app: {
    textAlign: 'center',
    padding: '1px',
    border: '2px solid black', // Добавляем рамку
    borderRadius: '10px', // Скругление углов
  },
  profileContainer: {
    position: 'relative',
    display: 'inline-block',
    textAlign: 'center',
  },
  logoContainer: {
    display: 'flex',
    alignItems: 'center',
  },
  profileLabel: {
    display: 'inline-block',
    cursor: 'pointer',
    position: 'relative',
  },
  profileInput: {
    display: 'none',
  },
  profileImage: {
    width: '40px',
    height: '40px',
    borderRadius: '50%',
    objectFit: 'cover',
  },
  defaultProfileImage: {
    width: '40px',
    height: '40px',
    borderRadius: '50%',
    backgroundColor: '#ccc',
  },
  buttonsContainer: {
    display: 'flex',
    gap: '40px',
    marginLeft: '90px', // Сдвигаем все кнопки влево
  },
  dropdownContainer: {
    position: 'relative',
    display: 'inline-block',
  },
  mainButton: {
    padding: '10px 20px',
    fontSize: '16px',
  },
  dropdownContent: {
    display: 'flex',
    flexDirection: 'column',
    position: 'absolute',
    backgroundColor: '#f9f9f9',
    minWidth: '160px',
    boxShadow: '0px 8px 16px 0px rgba(0,0,0,0.2)',
    zIndex: 1,
  },
  dropdownButton: {
    padding: '10px 20px',
    backgroundColor: '#fff',
    border: 'none',
    textAlign: 'left',
    cursor: 'pointer',
  }
};

const AppCSS = `
/* ваши стили */
`;

if (typeof document !== 'undefined') {
  const styleSheet = document.createElement('style');
  styleSheet.type = 'text/css';
  styleSheet.innerText = AppCSS;
  document.head.appendChild(styleSheet);
}
const AboutUs = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div 
      style={styles.dropdownContainer} 
      onMouseEnter={() => setIsOpen(true)} 
      onMouseLeave={() => setIsOpen(false)}
    >
      <button style={styles.mainButton}>О нас</button>
      {isOpen && (
        <div style={styles.dropdownContent}>
          <button style={styles.dropdownButton}>О проекте</button>
          <button style={styles.dropdownButton}>Наша команда</button>
        </div>
      )}
    </div>
  );
}
const Help = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div 
      style={styles.dropdownContainer} 
      onMouseEnter={() => setIsOpen(true)} 
      onMouseLeave={() => setIsOpen(false)}
    >
      <button style={styles.mainButton}>Помощь</button>
      {isOpen && (
        <div style={styles.dropdownContent}>
          <button style={styles.dropdownButton}>Задайте нам вопрос</button>
          <button style={styles.dropdownButton}>Как оплатить</button>
          <button style={styles.dropdownButton}>Политика конфиденциальности</button>
          <button style={styles.dropdownButton}>О проекте</button>
          <button style={styles.dropdownButton}>Студентам</button>
          <button style={styles.dropdownButton}>Учителям</button>
          <button style={styles.dropdownButton}>Прочее</button>
        </div>
      )}
    </div>
  );
}
const Profile = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div 
      style={styles.dropdownContainer} 
      onMouseEnter={() => setIsOpen(true)} 
      onMouseLeave={() => setIsOpen(false)}
    >
      <button style={styles.mainButton}>Каталог</button>
      {isOpen && (
        <div style={styles.dropdownContent}>
          <button style={styles.dropdownButton}>Инностранные языки</button>
          <button style={styles.dropdownButton}>Бизнес и менеджмент</button>
          <button style={styles.dropdownButton}>Творчество и дизайн</button>
          <button style={styles.dropdownButton}>Информационные технологии</button>
          <button style={styles.dropdownButton}>Педагогика</button>
        </div>
      )}
    </div>
  );
}
const ProfileImageButton = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [profileImage, setProfileImage] = useState<string | null>(null);

  const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setProfileImage(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <div 
      style={styles.profileContainer} 
      onMouseEnter={() => setIsOpen(true)} 
      onMouseLeave={() => setIsOpen(false)}
    >
      <label htmlFor="profile-image-upload" style={styles.profileLabel}>
        {profileImage ? (
          <img src={profileImage} alt="Profile" style={styles.profileImage} />
        ) : (
          <div style={styles.defaultProfileImage}></div>
        )}
        <input
          type="file"
          id="profile-image-upload"
          style={styles.profileInput}
          accept="image/*"
          onChange={handleImageUpload}
        />
      </label>
      {isOpen && (
        <div style={styles.dropdownContent}>
          <button style={styles.dropdownButton}>Перейти в профиль</button>
          <button style={styles.dropdownButton}>Баланс</button>
          <button style={styles.dropdownButton}>Выход</button>
        </div>
      )}
    </div>
  );
};
const Teaching = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div 
      style={styles.dropdownContainer} 
      onMouseEnter={() => setIsOpen(true)} 
      onMouseLeave={() => setIsOpen(false)}
    >
      <button style={styles.mainButton}>Преподавание</button>
      {isOpen && (
        <div style={styles.dropdownContent}>
          <button style={styles.dropdownButton}>Создать курс</button>
          <button style={styles.dropdownButton}>Редактировать курс</button>
        </div>
      )}
    </div>
  );
};
